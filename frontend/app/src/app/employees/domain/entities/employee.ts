import { Roles } from "./roles";

export class Employee
{
    public id:string;
    public username:string;
    public age: string;
    public doc:string;
    public email:string;
    public password:string;
    public roles:Roles[] = []
}