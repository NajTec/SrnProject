#pragma checksum "C:\Users\JanKl\Documents\TIM\SRN\srn06\BlazorClient\BlazorClient\BlazorClientNew\Pages\UserLogin.razor" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "cae44164000fc6572d2774d78651effb9a2d58f5"
// <auto-generated/>
#pragma warning disable 1591
namespace BlazorClientNew.Pages
{
    #line hidden
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Threading.Tasks;
    using Microsoft.AspNetCore.Components;
#nullable restore
#line 1 "C:\Users\JanKl\Documents\TIM\SRN\srn06\BlazorClient\BlazorClient\BlazorClientNew\_Imports.razor"
using System.Net.Http;

#line default
#line hidden
#nullable disable
#nullable restore
#line 2 "C:\Users\JanKl\Documents\TIM\SRN\srn06\BlazorClient\BlazorClient\BlazorClientNew\_Imports.razor"
using System.Net.Http.Json;

#line default
#line hidden
#nullable disable
#nullable restore
#line 3 "C:\Users\JanKl\Documents\TIM\SRN\srn06\BlazorClient\BlazorClient\BlazorClientNew\_Imports.razor"
using System.Text;

#line default
#line hidden
#nullable disable
#nullable restore
#line 4 "C:\Users\JanKl\Documents\TIM\SRN\srn06\BlazorClient\BlazorClient\BlazorClientNew\_Imports.razor"
using Microsoft.AspNetCore.Components.Forms;

#line default
#line hidden
#nullable disable
#nullable restore
#line 5 "C:\Users\JanKl\Documents\TIM\SRN\srn06\BlazorClient\BlazorClient\BlazorClientNew\_Imports.razor"
using Microsoft.AspNetCore.Components.Routing;

#line default
#line hidden
#nullable disable
#nullable restore
#line 6 "C:\Users\JanKl\Documents\TIM\SRN\srn06\BlazorClient\BlazorClient\BlazorClientNew\_Imports.razor"
using Microsoft.AspNetCore.Components.Web;

#line default
#line hidden
#nullable disable
#nullable restore
#line 7 "C:\Users\JanKl\Documents\TIM\SRN\srn06\BlazorClient\BlazorClient\BlazorClientNew\_Imports.razor"
using Microsoft.AspNetCore.Components.WebAssembly.Http;

#line default
#line hidden
#nullable disable
#nullable restore
#line 8 "C:\Users\JanKl\Documents\TIM\SRN\srn06\BlazorClient\BlazorClient\BlazorClientNew\_Imports.razor"
using Microsoft.JSInterop;

#line default
#line hidden
#nullable disable
#nullable restore
#line 9 "C:\Users\JanKl\Documents\TIM\SRN\srn06\BlazorClient\BlazorClient\BlazorClientNew\_Imports.razor"
using BlazorClientNew;

#line default
#line hidden
#nullable disable
#nullable restore
#line 10 "C:\Users\JanKl\Documents\TIM\SRN\srn06\BlazorClient\BlazorClient\BlazorClientNew\_Imports.razor"
using BlazorClientNew.Shared;

#line default
#line hidden
#nullable disable
#nullable restore
#line 11 "C:\Users\JanKl\Documents\TIM\SRN\srn06\BlazorClient\BlazorClient\BlazorClientNew\_Imports.razor"
using BlazorClientNew.Models;

#line default
#line hidden
#nullable disable
#nullable restore
#line 12 "C:\Users\JanKl\Documents\TIM\SRN\srn06\BlazorClient\BlazorClient\BlazorClientNew\_Imports.razor"
using BlazorClientNew.ViewModels;

#line default
#line hidden
#nullable disable
#nullable restore
#line 13 "C:\Users\JanKl\Documents\TIM\SRN\srn06\BlazorClient\BlazorClient\BlazorClientNew\_Imports.razor"
using BlazorClientNew.SingeltonInstances;

#line default
#line hidden
#nullable disable
#nullable restore
#line 14 "C:\Users\JanKl\Documents\TIM\SRN\srn06\BlazorClient\BlazorClient\BlazorClientNew\_Imports.razor"
using BlazorClientNew.Contracts;

#line default
#line hidden
#nullable disable
#nullable restore
#line 15 "C:\Users\JanKl\Documents\TIM\SRN\srn06\BlazorClient\BlazorClient\BlazorClientNew\_Imports.razor"
using BlazorClientNew.Services;

#line default
#line hidden
#nullable disable
#nullable restore
#line 16 "C:\Users\JanKl\Documents\TIM\SRN\srn06\BlazorClient\BlazorClient\BlazorClientNew\_Imports.razor"
using Radzen;

#line default
#line hidden
#nullable disable
#nullable restore
#line 17 "C:\Users\JanKl\Documents\TIM\SRN\srn06\BlazorClient\BlazorClient\BlazorClientNew\_Imports.razor"
using Radzen.Blazor;

#line default
#line hidden
#nullable disable
#nullable restore
#line 18 "C:\Users\JanKl\Documents\TIM\SRN\srn06\BlazorClient\BlazorClient\BlazorClientNew\_Imports.razor"
using Blazored.LocalStorage;

#line default
#line hidden
#nullable disable
    [Microsoft.AspNetCore.Components.RouteAttribute("/userlogin")]
    public partial class UserLogin : Microsoft.AspNetCore.Components.ComponentBase
    {
        #pragma warning disable 1998
        protected override void BuildRenderTree(Microsoft.AspNetCore.Components.Rendering.RenderTreeBuilder __builder)
        {
            __builder.AddMarkupContent(0, "<h3>UserLogin</h3>\r\n\r\n");
            __builder.OpenElement(1, "div");
            __builder.AddAttribute(2, "class", "container");
            __builder.AddMarkupContent(3, "\r\n    ");
            __builder.OpenElement(4, "div");
            __builder.AddAttribute(5, "class", "row");
            __builder.AddMarkupContent(6, "\r\n        ");
            __builder.OpenElement(7, "div");
            __builder.AddAttribute(8, "class", "flex-column");
            __builder.AddMarkupContent(9, "\r\n            ");
            __builder.AddMarkupContent(10, "<h3>Login</h3>\r\n            <br>\r\n            ");
            __builder.OpenComponent<Microsoft.AspNetCore.Components.Forms.EditForm>(11);
            __builder.AddAttribute(12, "Model", Microsoft.AspNetCore.Components.CompilerServices.RuntimeHelpers.TypeCheck<System.Object>(
#nullable restore
#line 16 "C:\Users\JanKl\Documents\TIM\SRN\srn06\BlazorClient\BlazorClient\BlazorClientNew\Pages\UserLogin.razor"
                             userLoginViewModel

#line default
#line hidden
#nullable disable
            ));
            __builder.AddAttribute(13, "ChildContent", (Microsoft.AspNetCore.Components.RenderFragment<Microsoft.AspNetCore.Components.Forms.EditContext>)((context) => (__builder2) => {
                __builder2.AddMarkupContent(14, "\r\n                ");
                __builder2.OpenElement(15, "div");
                __builder2.AddAttribute(16, "class", "row");
                __builder2.AddAttribute(17, "style", "padding:5px;");
                __builder2.AddMarkupContent(18, "\r\n                    ");
                __builder2.OpenComponent<Radzen.Blazor.RadzenLabel>(19);
                __builder2.AddAttribute(20, "Text", "Username: ");
                __builder2.CloseComponent();
                __builder2.AddMarkupContent(21, "\r\n                    ");
                __builder2.OpenComponent<Radzen.Blazor.RadzenTextBox>(22);
                __builder2.AddAttribute(23, "Value", Microsoft.AspNetCore.Components.CompilerServices.RuntimeHelpers.TypeCheck<System.String>(
#nullable restore
#line 19 "C:\Users\JanKl\Documents\TIM\SRN\srn06\BlazorClient\BlazorClient\BlazorClientNew\Pages\UserLogin.razor"
                                                userLoginViewModel.Username

#line default
#line hidden
#nullable disable
                ));
                __builder2.AddAttribute(24, "ValueChanged", Microsoft.AspNetCore.Components.CompilerServices.RuntimeHelpers.TypeCheck<Microsoft.AspNetCore.Components.EventCallback<System.String>>(Microsoft.AspNetCore.Components.EventCallback.Factory.Create<System.String>(this, Microsoft.AspNetCore.Components.CompilerServices.RuntimeHelpers.CreateInferredEventCallback(this, __value => userLoginViewModel.Username = __value, userLoginViewModel.Username))));
                __builder2.AddAttribute(25, "ValueExpression", Microsoft.AspNetCore.Components.CompilerServices.RuntimeHelpers.TypeCheck<System.Linq.Expressions.Expression<System.Func<System.String>>>(() => userLoginViewModel.Username));
                __builder2.CloseComponent();
                __builder2.AddMarkupContent(26, "\r\n                ");
                __builder2.CloseElement();
                __builder2.AddMarkupContent(27, "\r\n                <br>\r\n                ");
                __builder2.OpenElement(28, "div");
                __builder2.AddAttribute(29, "class", "row");
                __builder2.AddAttribute(30, "style", "padding:5px;");
                __builder2.AddMarkupContent(31, "\r\n                    ");
                __builder2.OpenComponent<Radzen.Blazor.RadzenLabel>(32);
                __builder2.AddAttribute(33, "Text", "Password: ");
                __builder2.CloseComponent();
                __builder2.AddMarkupContent(34, "\r\n                    ");
                __builder2.OpenComponent<Radzen.Blazor.RadzenPassword>(35);
                __builder2.AddAttribute(36, "Value", Microsoft.AspNetCore.Components.CompilerServices.RuntimeHelpers.TypeCheck<System.String>(
#nullable restore
#line 24 "C:\Users\JanKl\Documents\TIM\SRN\srn06\BlazorClient\BlazorClient\BlazorClientNew\Pages\UserLogin.razor"
                                                 userLoginViewModel.Password

#line default
#line hidden
#nullable disable
                ));
                __builder2.AddAttribute(37, "ValueChanged", Microsoft.AspNetCore.Components.CompilerServices.RuntimeHelpers.TypeCheck<Microsoft.AspNetCore.Components.EventCallback<System.String>>(Microsoft.AspNetCore.Components.EventCallback.Factory.Create<System.String>(this, Microsoft.AspNetCore.Components.CompilerServices.RuntimeHelpers.CreateInferredEventCallback(this, __value => userLoginViewModel.Password = __value, userLoginViewModel.Password))));
                __builder2.AddAttribute(38, "ValueExpression", Microsoft.AspNetCore.Components.CompilerServices.RuntimeHelpers.TypeCheck<System.Linq.Expressions.Expression<System.Func<System.String>>>(() => userLoginViewModel.Password));
                __builder2.CloseComponent();
                __builder2.AddMarkupContent(39, "\r\n                ");
                __builder2.CloseElement();
                __builder2.AddMarkupContent(40, "\r\n                <br>\r\n                ");
                __builder2.OpenElement(41, "div");
                __builder2.AddAttribute(42, "class", "row");
                __builder2.AddAttribute(43, "style", "padding:5px;");
                __builder2.AddMarkupContent(44, "\r\n                    ");
                __builder2.OpenElement(45, "Button");
                __builder2.AddAttribute(46, "onclick", Microsoft.AspNetCore.Components.EventCallback.Factory.Create<Microsoft.AspNetCore.Components.Web.MouseEventArgs>(this, 
#nullable restore
#line 28 "C:\Users\JanKl\Documents\TIM\SRN\srn06\BlazorClient\BlazorClient\BlazorClientNew\Pages\UserLogin.razor"
                                      () => OnLogin()

#line default
#line hidden
#nullable disable
                ));
                __builder2.AddContent(47, "Login");
                __builder2.CloseElement();
                __builder2.AddMarkupContent(48, "\r\n                    ");
                __builder2.OpenComponent<Microsoft.AspNetCore.Components.Routing.NavLink>(49);
                __builder2.AddAttribute(50, "href", "/register");
                __builder2.AddAttribute(51, "ChildContent", (Microsoft.AspNetCore.Components.RenderFragment)((__builder3) => {
                    __builder3.AddMarkupContent(52, "\r\n                        ");
                    __builder3.AddMarkupContent(53, "<button>Register</button>\r\n                    ");
                }
                ));
                __builder2.CloseComponent();
                __builder2.AddMarkupContent(54, "\r\n                ");
                __builder2.CloseElement();
                __builder2.AddMarkupContent(55, "\r\n            ");
            }
            ));
            __builder.CloseComponent();
            __builder.AddMarkupContent(56, "\r\n        ");
            __builder.CloseElement();
            __builder.AddMarkupContent(57, "\r\n    ");
            __builder.CloseElement();
            __builder.AddMarkupContent(58, "\r\n");
            __builder.CloseElement();
        }
        #pragma warning restore 1998
#nullable restore
#line 38 "C:\Users\JanKl\Documents\TIM\SRN\srn06\BlazorClient\BlazorClient\BlazorClientNew\Pages\UserLogin.razor"
       

    protected override void OnInitialized()
    {
        Client client = new Client();
        localStorage.SetItemAsync("client",client);
        base.OnInitialized();
    }

    void OnLogin()
    {
        Navigationmanager.NavigateTo($"/file");
        Console.WriteLine(userLoginViewModel.Username);
        Console.WriteLine(userLoginViewModel.Password);
    }

    void OnRegister()
    {

    }


#line default
#line hidden
#nullable disable
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private ILocalStorageService localStorage { get; set; }
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private Client Client { get; set; }
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private UserLoginViewModel userLoginViewModel { get; set; }
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private NavigationManager Navigationmanager { get; set; }
        [global::Microsoft.AspNetCore.Components.InjectAttribute] private HttpClient httpClient { get; set; }
    }
}
#pragma warning restore 1591
