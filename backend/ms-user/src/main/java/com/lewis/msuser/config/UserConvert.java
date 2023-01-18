package com.lewis.msuser.config;
import com.lewis.msuser.controllers.UsersController;
import com.lewis.msuser.entities.domain.User;
import com.lewis.msuser.entities.dto.UserDTO;
import com.lewis.msuser.entities.dto.UsersDTO;
import com.lewis.msuser.entities.models.PageModel;
import com.lewis.msuser.services.contracts.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class UserConvert {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    public List<UserDTO> toUsersDTO(List<User> usersEntity) {
        List<UserDTO> usersDTO = new ArrayList<>();
        for(User user: usersEntity)
        {
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            usersDTO.add(userDTO);
        }
        return usersDTO;
    }

    public  UsersDTO toUsersWithPagination(PageModel pageModel, Page<User> page, List<UserDTO> usersConvertedToDTO) {
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.getPage().setNumber(pageModel.getPagNumber());
        usersDTO.getPage().setSize(pageModel.getPagSize());
        usersDTO.getPage().setTotalPages(page.getTotalPages());
        usersDTO.getPage().setTotalElements(page.getTotalElements());
        usersDTO.set_embedded(usersConvertedToDTO);
        return usersDTO;

    }

    public UsersDTO toHateoas(UsersDTO usersDTO, PageModel pageModel) {
        List<EntityModel<UserDTO>> usersAddLink = StreamSupport.stream(usersDTO.get_embedded().spliterator(), false)
                .map(user -> EntityModel.of(user,
                  linkTo(methodOn(UsersController.class).get(pageModel)).withRel("GET-ALL-USERS")))
                .collect(Collectors.toList());

        usersDTO.get_embedded().clear();

        for(EntityModel user: usersAddLink)
        {
            UserDTO userDTO = modelMapper.map(user.getContent(), UserDTO.class);
            userDTO.setLinks(user.getLinks());
            usersDTO.get_embedded().add(userDTO);
        }
        return usersDTO;
    }

    public UserDTO toUserHATEOAS(User user)
    {
        EntityModel<User> entityModel = EntityModel.of(user);

        WebMvcLinkBuilder link =
                linkTo(methodOn(UsersController.class).getById(String.valueOf(user.id)));
        entityModel.add(link.withRel("get-user-by-id"));

        UserDTO userDTO = modelMapper.map(entityModel.getContent(), UserDTO.class);
        userDTO.setLinks(entityModel.getLinks());

        return userDTO;
    }
}
