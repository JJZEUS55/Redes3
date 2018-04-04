import time
from tkinter import *
from tkinter import ttk
import os
import VistaTabla

palabraConexion = ["OK LOGIN",
                   "OK DOWNLOAD",
                   "FAIL DOWNLOAD"]

mensajeDescargas = ["OK DOWNLOAD",
                    "FAIL DOWNLOAD"]

root = Tk()
menuFrame = Frame(root)
menuFrame.pack()

topFrame = Frame(root)
topFrame.pack()

botFrame = Frame(root)
botFrame.pack(side=BOTTOM)

# MENU
menuLbl = ttk.Label(menuFrame, text="Menu")
menuLbl.pack(side=LEFT)

opciones = ttk.Combobox(menuFrame, state="readonly")
opciones.pack(side=LEFT)
opciones["values"] = ["Nombre",
                      "Dia",
                      "Hora",
                      "Archivo"]

# TABLA de VISTA TABLA
tablaC = VistaTabla.App(botFrame)

# HORAS
horaLbl = ttk.Label(topFrame, text="Hora")
horaLbl.pack(side=LEFT)
horaTxt = ttk.Entry(topFrame)
horaTxt.pack(side=LEFT)

# MINUTOS
minLbl = ttk.Label(topFrame, text="Minutos")
minLbl.pack(side=LEFT)

minTxt = ttk.Entry(topFrame)
minTxt.pack(side=LEFT)

# NOMBRE
usuariosLbl = ttk.Label(topFrame, text="Usuario")
usuariosLbl.pack(side=LEFT)

usuarios = ttk.Combobox(topFrame, state="readonly")
usuarios.pack(side=LEFT)
usuarios["values"] = ["sammy",
                      "user1",
                      "jj1",
                      "user2"]

# DIAS
diaLbl = ttk.Label(topFrame, text="Dia")
diaLbl.pack(side=LEFT)

dias = ttk.Combobox(topFrame, state="readonly")
dias.pack(side=LEFT)
dias["values"] = ["Mon",
                  "Tue",
                  "Wed",
                  "Thu",
                  "Fri",
                  "Sat",
                  "Sun"]

# BOTONES
btnBuscarNombre = Button(topFrame, text="Buscar")
btnBuscarNombre.pack(side=RIGHT)
btnBuscarDia = Button(topFrame, text="Buscar")
btnBuscarDia.pack(side=RIGHT)
btnBuscarHora = Button(topFrame, text="Buscar")
btnBuscarHora.pack(side=RIGHT)
btnBuscarRecurso = Button(topFrame, text="Buscar")
btnBuscarRecurso.pack(side=RIGHT)


def desaparecer_botones():
    btnBuscarNombre.pack_forget()
    btnBuscarHora.pack_forget()
    btnBuscarRecurso.pack_forget()
    btnBuscarDia.pack_forget()

def selecciona_opc():
    opcSeleccionada = opciones.get()
    if opcSeleccionada == "Nombre":
        print(opcSeleccionada)
        desaparecer_botones()
        btnBuscarNombre = Button(topFrame, text="Buscar", command=busquedanombre)
        btnBuscarNombre.pack(side=RIGHT)
        tablaC.limpiarTabla()
        tablaC.CreateUI()
        desaparecer_widgets()
        usuariosLbl.pack(side=LEFT)
        usuarios.pack(side=LEFT)


    elif opcSeleccionada == "Dia":
        print(opcSeleccionada)
        desaparecer_botones()
        btnBuscarDia = Button(topFrame, text="Buscar", command=busquedadia)
        btnBuscarDia.pack(side=RIGHT)
        tablaC.limpiarTabla()
        tablaC.CreateUI()
        desaparecer_widgets()
        diaLbl.pack(side=LEFT)
        dias.pack(side=LEFT)

    elif opcSeleccionada == "Hora":
        print(opcSeleccionada)
        desaparecer_botones()
        btnBuscarHora = Button(topFrame, text="Buscar", command=busquedahora)
        btnBuscarHora.pack(side=RIGHT)
        tablaC.limpiarTabla()
        tablaC.CreateUI()
        desaparecer_widgets()
        horaLbl.pack(side=LEFT)
        horaTxt.pack(side=LEFT)
        minLbl.pack(side=LEFT)
        minTxt.pack(side=LEFT)

    elif opcSeleccionada == "Archivo":
        print(opcSeleccionada)
        desaparecer_botones()
        btnBuscarRecurso = Button(topFrame, text="Buscar", command=busqueda_recursos_accedidos)
        btnBuscarRecurso.pack(side=RIGHT)
        tablaC.limpiarTabla()
        tablaC.CreateUI()
        desaparecer_widgets()
        usuariosLbl.pack(side=LEFT)
        usuarios.pack(side=LEFT)


def desaparecer_widgets():
    horaTxt.pack_forget()
    horaLbl.pack_forget()
    minLbl.pack_forget()
    minTxt.pack_forget()

    diaLbl.pack_forget()
    dias.pack_forget()

    usuariosLbl.pack_forget()
    usuarios.pack_forget()


btnSeleccionarOpc = Button(menuFrame, text="Aceptar", command=selecciona_opc)
btnSeleccionarOpc.pack(side=LEFT)


def busquedanombre():
    openFile = open("vsftpd1.log", "r")
    tablaC.limpiarTabla()
    tablaC.CreateUI()
    contador = 0
    palabra = ""
    numLineas = sum(1 for line in open("vsftpd1.log"))
    nombre = usuarios.get()
    print("Usuarios Conectados")
    while 1:
        where = openFile.tell()
        line = openFile.readline()
        if not line:
            time.sleep(1)
            if numLineas == contador:
                break;
        else:
            if nombre in line:
                contador += 1
                print(line)
                if line in palabraConexion:
                    palabra = ""
                else:
                    palabra = line.split(" ")
                    # print(palabra)
                    formato_dividido(palabra)
            else:
                contador += 1


def busquedahora():
    openFile = open("vsftpd1.log", "r")
    tablaC.limpiarTabla()
    tablaC.CreateUI()
    contador = 0
    palabra = ""
    numLineas = sum(1 for line in open("vsftpd1.log"))
    hora = horaTxt.get()
    minutos = minTxt.get()
    horaCompleta = hora + ":" + minutos
    print("Usuarios Conectados")
    while 1:
        where = openFile.tell()
        line = openFile.readline()
        if not line:
            time.sleep(1)
            if numLineas == contador:
                break;
        else:
            if horaCompleta in line:
                contador += 1
                print(line)
                if line in palabraConexion:
                    palabra = ""
                else:
                    palabra = line.split(" ")
                    # print(palabra)
                    formato_dividido(palabra)
            else:
                contador += 1


def busquedadia():
    openFile = open("vsftpd1.log", "r")
    tablaC.limpiarTabla()
    tablaC.CreateUI()
    contador = 0
    numLineas = sum(1 for line in open("vsftpd1.log"))
    dia = dias.get()
    while 1:
        where = openFile.tell()
        line = openFile.readline()
        if not line:
            time.sleep(1)
            if numLineas == contador:
                break;
        else:
            if dia in line:
                contador += 1
                print(line)
            else:
                contador += 1


def usuariosconectados():
    openFile = open("vsftpd1.log", "r")
    contador = 0
    numLineas = sum(1 for line in open("vsftpd1.log"))
    valores = usuarios["values"]
    print("Usuarios Conectados")
    while 1:
        where = openFile.tell()
        line = openFile.readline()
        if not line:
            time.sleep(1)
            if numLineas == contador:
                break;
        else:
            if valores[0] in line and "terminated" in line:
                contador += 1
                print(line)
            else:
                contador += 1


def busqueda_recursos_accedidos():
    openFile = open("vsftpd1.log", "r")
    tablaC.limpiarTabla()
    tablaC.CreateUI2()
    nombre = usuarios.get()
    contador = 0
    palabra = ""
    numLineas = sum(1 for line in open("vsftpd1.log"))
    valores = usuarios["values"]
    print("Usuarios Conectados")
    while 1:
        where = openFile.tell()
        line = openFile.readline()
        if not line:
            time.sleep(1)
            if numLineas == contador:
                break;
        else:
            if nombre in line:
                contador += 1
                print(line)
                if line in palabraConexion:
                    palabra = ""
                else:
                    palabra = line.split(" ")
                    # print(palabra)
                    if "bytes" in line or "Kbyte" in line:
                        formato_dividido_recursos(palabra)
                        # print(palabra[9]) #OK or Fail
                        # print(palabra[10]) #Download
                        # print(palabra[13]) # Archivo
                        # print(palabra[14]) # Tamaño
                    # formato_dividido(palabra)
            else:
                contador += 1


# DIVIDE CON SPLIT TODA LA CADENA Y LA GUARDA EN UN ARREGLO
def dividir_frases():
    openFile = open("vsftpd1.log", "r")
    contador = 0
    palabra = ""
    numLineas = sum(1 for line in open("vsftpd1.log"))
    valores = usuarios["values"]
    print("Usuarios Conectados")
    while 1:
        where = openFile.tell()
        line = openFile.readline()
        if not line:
            time.sleep(1)
            if numLineas == contador:
                break;
        else:
            if valores[0] in line:
                contador += 1
                print(line)
                if line in palabraConexion:
                    palabra = ""
                else:
                    palabra = line.split(" ")
                    # print(palabra)
                    formato_dividido(palabra)
            else:
                contador += 1


# SOLO FORMATO DE IMPRESION y AGREGA VALORES A LA TABLA
def formato_dividido(palabra):
    diaSemana = palabra[0]
    mes = palabra[1]
    dia = palabra[3]
    horaCompleta = palabra[4]
    anio = palabra[5]
    usuario = palabra[8]

    # estado = palabra[9]
    # accion = palabra[10]
    # archivo = palabra[13]
    # tamano = palabra[14]

    tablaC.llenarTabla(diaSemana, mes, dia, horaCompleta, anio, usuario)

    print("Dia Semana: " + diaSemana)
    print("Mes: " + mes)
    print("Dia: " + dia)
    print("Hora: " + horaCompleta)
    print("Año: " + anio)
    print("Usuario: " + usuario)


def formato_dividido_recursos(palabra):
    diaSemana = palabra[0]
    mes = palabra[1]
    dia = palabra[3]
    horaCompleta = palabra[4]
    anio = palabra[5]
    usuario = palabra[8]

    estado = palabra[9]
    accion = palabra[10]
    archivo = palabra[13]
    tamano = palabra[14]

    tablaC.llenarTablaRecursos(diaSemana, mes, dia, horaCompleta, anio, usuario, estado, accion, archivo, tamano)

    print("Dia Semana: " + diaSemana)
    print("Mes: " + mes)
    print("Dia: " + dia)
    print("Hora: " + horaCompleta)
    print("Año: " + anio)
    print("Usuario: " + usuario)


root.mainloop()
