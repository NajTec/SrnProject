﻿using Caliburn.Micro;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SrnClient.ViewModels
{
    public class LoginViewModel : Conductor<object>
    {
        public void LoadFileManager() 
        {
            ActivateItemAsync(new FileViewModel());
        }
    }
}
