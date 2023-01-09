import { Roles } from '../entities/roles';
import { EmployeesDto } from './EmployeesDto';

export class EmployeeDto
{
    public id:string;
    public username:string;
    public age: string;
    public doc:string;
    public email:string;
    public roles:Roles[]
}