package com.lewis.msuser.entities.dto;
import java.util.ArrayList;
import java.util.List;

public class UsersDTO {
    private List<UserDTO> _embedded = new ArrayList<>();

    private PageDTO page = new PageDTO();

    public List<UserDTO> get_embedded() {
        return _embedded;
    }

    public void set_embedded(List<UserDTO> _embedded) {
        this._embedded = _embedded;
    }

    public PageDTO getPage() {
        return page;
    }

    public void setPage(PageDTO page) {
        this.page = page;
    }
}
