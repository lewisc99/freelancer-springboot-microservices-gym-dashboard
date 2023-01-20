import { CategoryDTO } from "./categoryDto";
import { UserDTO } from "./userDto";

export class PlanDTO
{
    public id:string;
    public start:Date;
    public finish:Date;
    public status:string;
    public categoryDto:CategoryDTO;
    public userDto:UserDTO;
}