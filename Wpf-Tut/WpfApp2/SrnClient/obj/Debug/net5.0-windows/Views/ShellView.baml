   M S B A M L   `   `   `  ����D  @SrnClient, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null1clr-namespace:SrnClient.ViewsSrnClient.Views  5          SrnClient.Views.ShellView   5   	   \ XPresentationFramework, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35e aSystem.Windows.Controls.Ribbon, Version=5.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089F 9http://schemas.microsoft.com/winfx/2006/xaml/presentation     5      R NSystem.Xaml, Version=5.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5      Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5      @d2http://schemas.microsoft.com/expression/blend/2008     5      /localclr-namespace:SrnClient.Views     5      
�������  � Title$  MainView��5	      $	��450��6   $	��800��6,   .��5
      �  � ColumnDefinitions 5   
   �� 5      $��20 �6   �� 5      $
��auto �6   �� 5      $
��auto �6   �� 5      $
��auto �6   �� 5      $
��auto �6   �� 5      $��* �6   �� 5      $��20 �6    � RowDefinitions 5   
   �� 5      $��20 �6   �� 5      $
��auto �6   �� 5      $
��auto �6   �� 5      $
��auto �6   �� 5      $
��auto �6   �� 5      $
��auto �6   �� 5      $��* �6   �� 5      $��20 �6   .+�5   
   �� $��1��6   $��1��6!   $��18(�61   $��0,0,0,10q�6?   
Srn-Client6]   �� 5!   
   $��2��6   $��1��6!   $��18(�61   $��0,0,0,10q�6?   Name des User:6a   �� 5"   
   $��2��6@   $��2��6M   $��18(�6]   ��6   ��  �� Path$ UserName� �� Mode$ OneWay=�6m   �� 5$   
   -   5         ��Name$ 	LoadLogin��5$      $��3��6$   $��1��61   $��0,0,0,10q�6A   .��6S   	LoginPage6^   �� 5%   
   -   5        $ LoadRegister��5%      $��3��6'   $��2��64   $��0,0,0,10q�6D   $��1��6V   .��6j   RegisterPage6x   �� 5'   
   -   5         ��Name$ 
ActiveItem��5'   J   $��4��6   $��1��6&   $��5��66   $��3��6^   6q   5)      5*      