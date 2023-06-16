import tkinter
import customtkinter

customtkinter.set_appearance_mode('system')
customtkinter.set_default_color_theme('blue')

app = customtkinter.CTk()

app.geometry('400x300')


def button_function():
    exit()


# Use CTkButton instead of tkinter Button
button = customtkinter.CTkButton(master=app, text="Application", command=button_function)
button.place(relx=0.5, rely=0.5, anchor=tkinter.CENTER)

app.mainloop()
