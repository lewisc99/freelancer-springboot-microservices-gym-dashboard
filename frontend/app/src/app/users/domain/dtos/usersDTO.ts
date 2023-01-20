import { PageDTO } from './pageDTO';
import { UserDTO } from './userDTO';

export class UsersDTO
{
    public _embedded:UserDTO[];
    public pageDto:PageDTO;
}