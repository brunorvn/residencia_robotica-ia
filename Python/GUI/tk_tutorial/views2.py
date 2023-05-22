from views import View

class Table(View):
    def __init__(self, master=None):
        super().__init__(master)
        self.master = master
        self.rowconfigure(0, weight=1)
        self.columnconfigure(0, weight=1)
        self.grid(row=0, column=0, sticky=tk.N + tk.S + tk.E + tk.W)
        self.create_button()


    def create_view(self, data: List):
        frame = tk.Frame(self)
        frame.rowconfigure(0, weight=1)
        frame.columnconfigure(0, weight=1)
        frame.grid(row=0, column=0, sticky=tk.N + tk.S + tk.E + tk.W)
        total_rows = len(data)
        total_columns = len(data[0])
        for row in range(total_rows):
            for col in range(total_columns):
                entry = tk.Entry(
                    frame,
                    width=10,
                )

                entry.grid(row=row, column=col, sticky=tk.N + tk.S + tk.E + tk.W)
                entry.rowconfigure(0, weight=1)
                entry.columnconfigure(0, weight=1)
                entry.insert(tk.END, data[i][j])


    def create_button(self):
        frame = tk.Frame(self)
        frame.rowconfigure(0, weight=1)
        frame.columnconfigure(0, weight=1)
        self.button = tk.Button(frame)
        self.button["text"] = "refresh"
        self.button.grid(row=0, column=0, sticky=tk.N + tk.S + tk.E + tk.W)
        frame.grid(row=1, column=0, sticky=tk.N + tk.S + tk.E + tk.W)