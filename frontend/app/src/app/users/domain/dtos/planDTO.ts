import { CategoryDTO } from "./categoryDTO";
import { UserDTO } from "./userDTO";

export class PlanDTO
{
    public id:string;
    public start:Date;
    public finish:Date;
    public status:string = "";
    public category:CategoryDTO = new CategoryDTO();
    public user:UserDTO;
}