import tkinter as tk
from tkinter import ttk
import customtkinter as ctk

root = ctk.CTk()
root.title("Program | Convertor")
root.geometry("600x480")


class Convertor(object):
    def __init__(self, master):
        frame = master
        frame.pack()

        self.label = ctk.CTkLabel(master, font=())

        self.my_button = ctk.CTkButton(master, text="Click it", command=self.button_func)
        self.my_button.pack(padx=8,
                            pady=16)

    def button_func(self):
        entry.get()


# create a window
# window = tk.Tk()
# window.title('Demo || Convertor')
# window.geometry('600x480')

# ttk label
# label = ctk.CTkLabel(master)
# Label(master=window,
# text='Miles to Kilometers',
# font="Calibri 24 bold")
# label.pack()

# tk.text
text = tk.Text(master=window)
text.pack()

# ttk entry
entry = ttk.Entry(master=window)
entry.pack(side="left", padx=10)

# exercise label
exercise_label = ttk.Label(master=window, text="my label")
exercise_label.pack()

# ttk button
button = ttk.Button(master=window, text='Convert', command=button_func)
button.pack()

# exercise
# add one more text label and a button with a function that prints 'hello'
# the label should say "my label" and be between the entry widget and the button

# exercise button
# exercise_button = ttk.Button(master = window, text = 'exercise button', command = exercise_button_func)
# exercise_button = ttk.Button(master=window, text='exercise button', command=lambda: print('hello'))
# exercise_button.pack()

# run
root.mainloop()
