import { PageDTO } from './pageDTO';
import { UserDTO } from './userDto';

export class UsersDTO
{
    public _embedded:UserDTO[];
    public pageDto:PageDTO;
}