try:
    from Tkinter import *
    from ttk import *
except ImportError:  # Python 3
    from tkinter import *
    from tkinter.ttk import *


class App(Frame):

    def __init__(self, parent):
        Frame.__init__(self, parent)
        self.CreateUI()
        #self.LoadTable()
        self.grid(sticky = (N,S,W,E))
        parent.grid_rowconfigure(0, weight = 1)
        parent.grid_columnconfigure(0, weight = 1)

    def CreateUI(self):
        tv = Treeview(self)
        tv['columns'] = ('diasemana', 'mes', 'dia', 'hora', 'anio')
        tv.heading("#0", text='Usuario', anchor='w')
        tv.column("#0", anchor="w")
        tv.heading('diasemana', text='Dia Semana')
        tv.column('diasemana', anchor='center', width=100)

        tv.heading('mes', text='Mes')
        tv.column('mes', anchor='center', width=100)

        tv.heading('dia', text='Dia')
        tv.column('dia', anchor='center', width=100)

        tv.heading('hora', text='Hora')
        tv.column('hora', anchor='center', width=100)

        tv.heading('anio', text='Año')
        tv.column('anio', anchor='center', width=100)

        tv.grid()
        self.treeview = tv
        self.grid_rowconfigure(0, weight = 1)
        self.grid_columnconfigure(0, weight = 1)

    def LoadTable(self):
        self.treeview.insert('', 'end', text="First", values=('10:00',
                             '10:10', 'Ok'))
        self.treeview.insert('', 'end', text="Seccond", values=('10:00',
                                                              '10:10', 'Ok'))

    def llenarTabla(self, diaSemana, mes, dia, horaCompleta, anio, usuario):
        self.treeview.insert('', 'end', text=usuario, values=(diaSemana, mes, dia, horaCompleta, anio))
        #print("recibi de lado Vista" + usuario +dia + diaSemana +horaCompleta)




def main():
    root = Tk()
    App(root)
    root.mainloop()

if __name__ == '__main__':
    main()