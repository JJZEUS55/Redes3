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
        # if numero == 0:
        #     self.CreateUI()
        # else:
        #     self.CreateUI2()
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

    def CreateUI2(self):
        tv = Treeview(self)
        tv['columns'] = ('diasemana', 'mes', 'dia', 'hora', 'anio', 'estado', 'accion', 'archivo', 'tamano')
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

        tv.heading('estado', text='Estado')
        tv.column('estado', anchor='center', width=100)

        tv.heading('accion', text='Accion')
        tv.column('accion', anchor='center', width=100)

        tv.heading('archivo', text='Archivo')
        tv.column('archivo', anchor='center', width=100)

        tv.heading('tamano', text='Tamaño')
        tv.column('tamano', anchor='center', width=100)

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

    def llenarTablaRecursos(self, diaSemana, mes, dia, horaCompleta, anio, usuario, estado, accion, archivo, tamano):
        self.treeview.insert('', 'end', text=usuario, values=(diaSemana, mes, dia, horaCompleta, anio, estado, accion, archivo, tamano))
        #print("recibi de lado Vista" + usuario +dia + diaSemana +horaCompleta)

    def limpiarTabla(self):
        self.treeview.grid_remove()



def main():
    root = Tk()
    App(root)
    root.mainloop()

if __name__ == '__main__':
    main()