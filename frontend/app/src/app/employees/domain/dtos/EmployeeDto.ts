import { Roles } from '../entities/roles';

export class EmployeeDto
{
    public id:string;
    public username:string;
    public age: string;
    public doc:string;
    public email:string;
    public roles:Roles[]
}