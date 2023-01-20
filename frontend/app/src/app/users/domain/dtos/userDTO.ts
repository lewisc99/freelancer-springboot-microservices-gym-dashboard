import { Links } from './links';
import { PlanDTO } from './planDTO';

export class UserDTO
{
    public id:string;
    public username:string;
    public age:number;
    public doc: string;
    public email:string;
    public plan:PlanDTO ;
    public links:Links;
}