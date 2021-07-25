using BlazorClientNew.Contracts;
using BlazorClientNew.Models;
using BlazorClientNew.SingeltonInstances;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;

namespace BlazorClientNew.ViewModels
{
    public class UserRegisterViewModel
    {
        #region fields
        private UserDTO _userDTO;
        private ICrypto Crypto;
        #endregion

        #region Properties
        public string FirstName
        {
            get
            {
                return _userDTO._firstname;
            }
            set
            {
                _userDTO._firstname = value;
            }
        }
        public string Password
        {
            get
            {
                return _userDTO._password;
            }
            set
            {
                _userDTO._password = value;
            }
        }

        public string LastName
        {
            get
            {
                return _userDTO._lastname;
            }
            set
            {
                _userDTO._lastname = value;
            }
        }
        public string Birthday
        {
            get
            {
                return _userDTO._birthday;
            }
            set
            {
                _userDTO._birthday = value;
            }
        }

        public string Country
        {
            get
            {
                return _userDTO._country;
            }
            set
            {
                _userDTO._country = value;
            }
        }

        public string Email
        {
            get
            {
                return _userDTO._email;
            }
            set
            {
                _userDTO._email = value;
            }
        }

        public string Gender
        {
            get
            {
                return _userDTO._gender;
            }
            set
            {
                _userDTO._gender = value;
            }
        }

        public string Street
        {
            get
            {
                return _userDTO._street;
            }
            set
            {
                _userDTO._street = value;
            }
        }

        public string Session
        {
            get
            {
                return _userDTO._session;
            }
            set
            {
                _userDTO._session = value;
            }
        }

        public string State
        {
            get
            {
                return _userDTO._state;
            }
            set
            {
                _userDTO._state = value;
            }
        }
        #endregion

        #region Constructors
        public UserRegisterViewModel(Client client,ICrypto crypto)
        {
            _userDTO = new UserDTO();
            Crypto = crypto;
        }
        #endregion

        #region Methods
        public UserDTO GetUser()
        {
            return _userDTO;
        }
        public async Task Register(Client client)
        {
            client.PublicPrivateKeyPair = new PrivatePublicPair();
            //client.PublicPrivateKeyPair.rsa = Crypto.GenerateRSA();
            string[] field = Crypto.GenerateRSA2();
            try
            {
                Console.WriteLine(field[0]);
                Console.WriteLine(field[1]);
            }
            catch (PlatformNotSupportedException e) { 
            
            }
            // throw new NotImplementedException();
        }
        #endregion
    }
}
