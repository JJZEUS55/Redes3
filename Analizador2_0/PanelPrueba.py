# -*- coding: utf-8 -*-

###########################################################################
## Python code generated with wxFormBuilder (version Jun 17 2015)
## http://www.wxformbuilder.org/
##
## PLEASE DO "NOT" EDIT THIS FILE!
###########################################################################

import wx
import wx.xrc
import wx.grid


###########################################################################
## Class MyFrame2
###########################################################################

class MyFrame2(wx.Frame):

    def __init__(self, parent):
        wx.Frame.__init__(self, parent, id=wx.ID_ANY, title=wx.EmptyString, pos=wx.DefaultPosition,
                          size=wx.Size(1034, 509), style=wx.DEFAULT_FRAME_STYLE | wx.TAB_TRAVERSAL)

        self.SetSizeHintsSz(wx.DefaultSize, wx.DefaultSize)

        bSizer3 = wx.BoxSizer(wx.VERTICAL)

        self.m_staticText3 = wx.StaticText(self, wx.ID_ANY, u"Opciones de Busqueda", wx.DefaultPosition, wx.DefaultSize,
                                           0)
        self.m_staticText3.Wrap(-1)
        bSizer3.Add(self.m_staticText3, 0, wx.ALL | wx.ALIGN_CENTER_HORIZONTAL, 5)

        m_comboBox1Choices = [u"Firewall", u"Archivos", u"General"]
        self.m_comboBox1 = wx.ComboBox(self, wx.ID_ANY, u"Alertas\n", wx.DefaultPosition, wx.Size(300, -1),
                                       m_comboBox1Choices, 0)
        bSizer3.Add(self.m_comboBox1, 0, wx.ALL | wx.ALIGN_CENTER_HORIZONTAL, 5)

        self.btnBuscar = wx.Button(self, wx.ID_ANY, u"Buscar Registros", wx.DefaultPosition, wx.DefaultSize, 0)
        bSizer3.Add(self.btnBuscar, 0, wx.ALL | wx.ALIGN_CENTER_HORIZONTAL, 5)

        self.m_grid2 = wx.grid.Grid(self, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, 0)

        # Grid
        self.m_grid2.CreateGrid(5, 5)
        self.m_grid2.EnableEditing(True)
        self.m_grid2.EnableGridLines(True)
        self.m_grid2.EnableDragGridSize(False)
        self.m_grid2.SetMargins(0, 0)

        # Columns
        self.m_grid2.EnableDragColMove(False)
        self.m_grid2.EnableDragColSize(True)
        self.m_grid2.SetColLabelSize(30)
        self.m_grid2.SetColLabelAlignment(wx.ALIGN_CENTRE, wx.ALIGN_CENTRE)

        # Rows
        self.m_grid2.EnableDragRowSize(True)
        self.m_grid2.SetRowLabelSize(80)
        self.m_grid2.SetRowLabelAlignment(wx.ALIGN_CENTRE, wx.ALIGN_CENTRE)

        # Label Appearance

        # Cell Defaults
        self.m_grid2.SetDefaultCellAlignment(wx.ALIGN_LEFT, wx.ALIGN_TOP)
        bSizer3.Add(self.m_grid2, 0, wx.ALL | wx.ALIGN_CENTER_HORIZONTAL, 5)

        self.SetSizer(bSizer3)
        self.Layout()

        self.Centre(wx.BOTH)

    def __del__(self):
        pass


app = wx.App(False)
frame = MyFrame2(None)
frame.Show(True)
app.MainLoop()
