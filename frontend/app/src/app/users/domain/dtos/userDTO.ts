import { Links } from './links';
import { PlanDTO } from './planDto';
export class UserDTO
{
    public id:string;
    public username:string;
    public age:number;
    public doc: string;
    public email:string;
    public planDto:PlanDTO;
    public links:Links;

}