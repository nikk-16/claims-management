import { UsersService } from './users.service';
import { of } from 'rxjs';
import { AppConstant } from '../util/constants';

describe('UsersService', () => {
  let service: UsersService;
  let httpClient = { get: jest.fn(), post: jest.fn(), put: jest.fn() };
  let router = { navigate: jest.fn() };
  beforeEach(() => {
    jest.clearAllMocks();
    service = new UsersService(httpClient as any, router as any);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('getAllUsers should return all the users', () => {
    const result = [{
      "username": "meghana",
      "id": "1234567654321"
    }]
    httpClient.get.mockReturnValue(of(result));
    service.getAllUsers().subscribe(response => {
      expect(response).toEqual(result);
    })
    expect(httpClient.get).toHaveBeenCalledWith(`${AppConstant.USERS_URI}/getAll`)
  })

  it('getUserById should get user according to given id', () => {
    const result = [{ 'id': "123" }]
    const username = "testuser";
    httpClient.get.mockReturnValue(of(result));
    service.getUserByUsername(username).subscribe(response => {
      expect(response).toEqual(result);
    })
    expect(httpClient.get).toHaveBeenCalledWith(`${AppConstant.USERS_URI}/user/${username}`);
  });

  it('signUp should post user data for registration', () => {
    const userData = { username: 'testuser', password: 'password' };
    const response = { status: 'success', message: 'User registered successfully' };
    httpClient.post.mockReturnValue(of(response));
    service.signUp(userData).subscribe(data => {
      expect(data).toEqual(response);
    });
    expect(httpClient.post).toHaveBeenCalledWith(
      `${AppConstant.USERS_URI}/signup`,
      userData,
      expect.objectContaining({
        responseType: 'json'
      })
    );
    expect((httpClient.post.mock.calls[0][2] as any).headers.get('Content-Type')).toEqual('application/json');
  });

  it('login should post user data for authentication', () => {
    const username = 'testuser';
    const password = 'password';
    const body = { username, password };
    const response = { message: 'User authenticated successfully', token: 'tfadvgque4567' };
    httpClient.post.mockReturnValue(of(response));
    service.login(username, password).subscribe(data => {
      expect(data).toEqual(response);
    });
    expect(httpClient.post).toHaveBeenCalledWith(`http://localhost:8080/users/login`, body);
  })

  it('getUserByUsername should get user according to given username', () => {
    const username = "meghana";
    const result = [{ 'username': username }]
    httpClient.get.mockReturnValue(of(result));
    service.getUserByUsername(username).subscribe(response => {
      expect(response).toEqual(result);
    })
    expect(httpClient.get).toHaveBeenCalledWith(`${AppConstant.USERS_URI}/user/${username}`);
  });

  

});
