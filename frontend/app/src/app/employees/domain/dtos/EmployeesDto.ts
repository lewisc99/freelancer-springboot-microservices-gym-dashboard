import { EmployeeDto } from "./EmployeeDto";
import { Link } from "./links";
import { Page } from "./page";
export class EmployeesDto
{
    public _embedded: EmployeeDto[];
    public links: Link[];
    public page:Page;
}