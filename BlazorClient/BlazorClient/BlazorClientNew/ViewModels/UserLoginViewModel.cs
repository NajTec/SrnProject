using BlazorClientNew.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BlazorClientNew.ViewModels
{
    public class UserLoginViewModel 
    {
    private LoginDTO _loginDTO;

    public string Username
    {
        get
        {
            return _loginDTO.Login;
        }
        set
        {
            _loginDTO.Login = value;
        }
    }

    public string Password
    {
        get
        {
            return _loginDTO.Password;
        }
        set
        {
            _loginDTO.Password = value;
        }
    }

    public UserLoginViewModel()
    {
        _loginDTO = new LoginDTO();
    }
    }
}

