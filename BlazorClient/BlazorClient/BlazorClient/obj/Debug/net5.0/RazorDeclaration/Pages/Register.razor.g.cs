// <auto-generated/>
#pragma warning disable 1591
#pragma warning disable 0414
#pragma warning disable 0649
#pragma warning disable 0169

namespace BlazorClient.Pages
{
    #line hidden
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Threading.Tasks;
    using Microsoft.AspNetCore.Components;
#nullable restore
#line 1 "C:\Users\JanKl\Documents\GitHub\SrnProject\BlazorClient\BlazorClient\BlazorClient\_Imports.razor"
using System.Net.Http;

#line default
#line hidden
#nullable disable
#nullable restore
#line 2 "C:\Users\JanKl\Documents\GitHub\SrnProject\BlazorClient\BlazorClient\BlazorClient\_Imports.razor"
using System.Net.Http.Json;

#line default
#line hidden
#nullable disable
#nullable restore
#line 3 "C:\Users\JanKl\Documents\GitHub\SrnProject\BlazorClient\BlazorClient\BlazorClient\_Imports.razor"
using Microsoft.AspNetCore.Components.Authorization;

#line default
#line hidden
#nullable disable
#nullable restore
#line 4 "C:\Users\JanKl\Documents\GitHub\SrnProject\BlazorClient\BlazorClient\BlazorClient\_Imports.razor"
using Microsoft.AspNetCore.Components.Forms;

#line default
#line hidden
#nullable disable
#nullable restore
#line 5 "C:\Users\JanKl\Documents\GitHub\SrnProject\BlazorClient\BlazorClient\BlazorClient\_Imports.razor"
using Microsoft.AspNetCore.Components.Routing;

#line default
#line hidden
#nullable disable
#nullable restore
#line 6 "C:\Users\JanKl\Documents\GitHub\SrnProject\BlazorClient\BlazorClient\BlazorClient\_Imports.razor"
using Microsoft.AspNetCore.Components.Web;

#line default
#line hidden
#nullable disable
#nullable restore
#line 7 "C:\Users\JanKl\Documents\GitHub\SrnProject\BlazorClient\BlazorClient\BlazorClient\_Imports.razor"
using Microsoft.AspNetCore.Components.Web.Virtualization;

#line default
#line hidden
#nullable disable
#nullable restore
#line 8 "C:\Users\JanKl\Documents\GitHub\SrnProject\BlazorClient\BlazorClient\BlazorClient\_Imports.razor"
using Microsoft.AspNetCore.Components.WebAssembly.Http;

#line default
#line hidden
#nullable disable
#nullable restore
#line 9 "C:\Users\JanKl\Documents\GitHub\SrnProject\BlazorClient\BlazorClient\BlazorClient\_Imports.razor"
using Microsoft.JSInterop;

#line default
#line hidden
#nullable disable
#nullable restore
#line 10 "C:\Users\JanKl\Documents\GitHub\SrnProject\BlazorClient\BlazorClient\BlazorClient\_Imports.razor"
using BlazorClient;

#line default
#line hidden
#nullable disable
#nullable restore
#line 11 "C:\Users\JanKl\Documents\GitHub\SrnProject\BlazorClient\BlazorClient\BlazorClient\_Imports.razor"
using BlazorClient.Models;

#line default
#line hidden
#nullable disable
#nullable restore
#line 12 "C:\Users\JanKl\Documents\GitHub\SrnProject\BlazorClient\BlazorClient\BlazorClient\_Imports.razor"
using BlazorClient.Shared;

#line default
#line hidden
#nullable disable
#nullable restore
#line 13 "C:\Users\JanKl\Documents\GitHub\SrnProject\BlazorClient\BlazorClient\BlazorClient\_Imports.razor"
using BlazorClient.ViewModel;

#line default
#line hidden
#nullable disable
#nullable restore
#line 14 "C:\Users\JanKl\Documents\GitHub\SrnProject\BlazorClient\BlazorClient\BlazorClient\_Imports.razor"
using BlazorClient.Services;

#line default
#line hidden
#nullable disable
#nullable restore
#line 15 "C:\Users\JanKl\Documents\GitHub\SrnProject\BlazorClient\BlazorClient\BlazorClient\_Imports.razor"
using Radzen;

#line default
#line hidden
#nullable disable
#nullable restore
#line 16 "C:\Users\JanKl\Documents\GitHub\SrnProject\BlazorClient\BlazorClient\BlazorClient\_Imports.razor"
using Radzen.Blazor;

#line default
#line hidden
#nullable disable
    [Microsoft.AspNetCore.Components.RouteAttribute("/register")]
    public partial class Register : Microsoft.AspNetCore.Components.ComponentBase
    {
        #pragma warning disable 1998
        protected override void BuildRenderTree(Microsoft.AspNetCore.Components.Rendering.RenderTreeBuilder __builder)
        {
        }
        #pragma warning restore 1998
#nullable restore
#line 76 "C:\Users\JanKl\Documents\GitHub\SrnProject\BlazorClient\BlazorClient\BlazorClient\Pages\Register.razor"
       
    const string male = "männlich";
    const string female = "weiblich";
    const string divers = "divers";
    DateTime? value = DateTime.Now;

    protected override void OnInitialized()
    {
        base.OnInitialized();
    }

    private void OnChange(DateTime? value, string name, string format)
    {
        Console.WriteLine("triggered");
        Console.WriteLine(value?.ToString());
        //userRegisterViewModel.Birthday = Date.ToString();
    }


#line default
#line hidden
#nullable disable
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private UserDTO user { get; set; }
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private HttpClient httpClient { get; set; }
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private UserRegisterViewModel userRegisterViewModel { get; set; }
    }
}
#pragma warning restore 1591