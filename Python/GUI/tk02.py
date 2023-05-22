import tkinter as tk

root = tk.Tk()

class Application(object):
    def __init__(self) -> None:
        self.root = root
        self.screen()
        #self.labels()
        self.buttons()
        root.mainloop()
    
    def screen(self):
        self.root.title("Robot aplication")
        self.root.configure(background="#003333")
        self.root.geometry("800x640")

    def labels(self):
        self.my_label = tk.Label(self.root,
                                 text="Testing Labels from my application",
                                 font="Tahoma",
                                 
                                 )
        self.my_label.grid(column=0, 
                           columnspan=2,
                           row=1)
    
    def buttons(self):
        my_buttons = tk.Button(self.root, text="Text()")
        my_buttons.grid(column=0, row=2)

if __name__ == '__main__':
    Application()
    
    
    