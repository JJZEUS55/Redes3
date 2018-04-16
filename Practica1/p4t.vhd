LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
use std.textio.all;
use ieee.Numeric_std.all;
use ieee.std_logic_unsigned.all;
use ieee.std_logic_arith.all;
use ieee.std_logic_textio.all;

 
ENTITY T_p4t_File IS
END T_p4t_File;
 
ARCHITECTURE behavior OF T_p4t_File IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT p4
    PORT(
         readR : IN  std_logic_vector(3 downto 0);
         readR2 : IN  std_logic_vector(3 downto 0);
         writeRegister : IN  std_logic_vector(3 downto 0);
         SHAMT : IN  std_logic_vector(3 downto 0);
         writeData : IN  std_logic_vector(15 downto 0);
         readData1 : OUT  std_logic_vector(15 downto 0);
         readData2 : OUT  std_logic_vector(15 downto 0);
         SHE : IN  std_logic;
         dir : IN  std_logic;
         WR : IN  std_logic;
         clk : IN  std_logic;
         clr : IN  std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal readR : std_logic_vector(3 downto 0) := (others => '0');
   signal readR2 : std_logic_vector(3 downto 0) := (others => '0');
   signal writeRegister : std_logic_vector(3 downto 0) := (others => '0');
   signal SHAMT : std_logic_vector(3 downto 0) := (others => '0');
   signal writeData : std_logic_vector(15 downto 0) := (others => '0');
   signal SHE : std_logic := '0';
   signal dir : std_logic := '0';
   signal WR : std_logic := '0';
   signal clk : std_logic := '0';
   signal clr : std_logic := '0';

 	--Outputs
   signal readData1 : std_logic_vector(15 downto 0);
   signal readData2 : std_logic_vector(15 downto 0);

   -- Clock period definitions
   constant clk_period : time := 10 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: p4 PORT MAP (
          readR => readR,
          readR2 => readR2,
          writeRegister => writeRegister,
          SHAMT => SHAMT,
          writeData => writeData,
          readData1 => readData1,
          readData2 => readData2,
          SHE => SHE,
          dir => dir,
          WR => WR,
          clk => clk,
          clr => clr
        );

   -- Clock process definitions
   clk_process :process
   begin
		clk <= '0';
		wait for clk_period/2;
		clk <= '1';
		wait for clk_period/2;
   end process;
	
	-- Stimulus process
   stim_proc: process
   
		--Entradas
		FILE archivoEntrada : TEXT;
		VARIABLE lineaEntrada : LINE;
		
		VARIABLE var_clr, var_she, var_dir, var_wr : STD_LOGIC;
		VARIABLE var_readR, var_readR2: STD_LOGIC_VECTOR ( 3 DOWNTO 0 );
		VARIABLE var_WriteRegister, var_SHAMT : STD_LOGIC_VECTOR ( 3 DOWNTO 0 );
		VARIABLE var_WriteData : STD_LOGIC_VECTOR ( 15 DOWNTO 0 );
		
		--Salidas
		FILE archivoSalida : TEXT;
		VARIABLE lineaOut : LINE;
		
		VARIABLE var_ReadData1, var_ReadData2 : STD_LOGIC_VECTOR ( 15 DOWNTO 0 );

		VARIABLE cadena : STRING ( 1 TO 4 );
		VARIABLE cadena1 : STRING ( 1 TO 5 );
		VARIABLE cadena2 : STRING ( 1 TO 2 );
	begin
		
		FILE_OPEN (archivoEntrada, "in.txt", READ_MODE);
		FILE_OPEN (archivoSalida, "out.txt", WRITE_MODE);
		
		cadena := " RR1";
		WRITE ( lineaOut, cadena, RIGHT, cadena'LENGTH+1 );
		cadena := " RR2";
		WRITE ( lineaOut, cadena, RIGHT, cadena'LENGTH+1 );
		cadena2 := "SH";
		WRITE ( lineaOut, cadena2, RIGHT, cadena2'LENGTH+1 );
		cadena := "WREG";
		WRITE ( lineaOut, cadena, RIGHT, cadena'LENGTH+1 );
		cadena := "  WD";
		WRITE ( lineaOut, cadena, RIGHT, cadena'LENGTH+1 );
		cadena := "  WR";
		WRITE ( lineaOut, cadena, RIGHT, cadena'LENGTH+1 );
		cadena := " SHE";
		WRITE ( lineaOut, cadena, RIGHT, cadena'LENGTH+1 );
		cadena := " dir";
		WRITE ( lineaOut, cadena, RIGHT, cadena'LENGTH+1 );
		cadena := " RD1";
		WRITE ( lineaOut, cadena, RIGHT, cadena'LENGTH+1 );
		cadena := " RD2";
		WRITE ( lineaOut, cadena, RIGHT, cadena'LENGTH+1 );
		
		WRITELINE (archivoSalida, lineaOut);
		
		WAIT FOR 10 NS;
		--Se lee hasta encontrar el fin del archivo
		WHILE NOT ENDFILE (archivoEntrada) LOOP
			
			READLINE (archivoEntrada,lineaEntrada);
			
			HREAD(lineaEntrada, var_readR);
			readR <= var_readR;
			
			HREAD(lineaEntrada, var_readR2);
			readR2 <= var_readR2;
			
			HREAD(lineaEntrada, var_SHAMT);
			SHAMT <= var_SHAMT;
			
			HREAD(lineaEntrada, var_WriteRegister);
			WriteRegister <= var_WriteRegister;
			
			HREAD(lineaEntrada, var_WriteData );
			WriteData <= var_WriteData;
			
			READ(lineaEntrada, var_wr);
			wr <= var_wr;
			
			READ(lineaEntrada, var_she);
			SHE <= var_she;
			
			READ(lineaEntrada,var_dir);
			dir <= var_dir;
			
			READ(lineaEntrada, var_clr );
			clr <= var_clr;
			
			WAIT UNTIL RISING_EDGE ( clk );
			
			var_ReadData1 := ReadData1;
			var_ReadData2 := ReadData2;
			
			--Se escriben entradas
			HWRITE(lineaOut, var_readR, RIGHT, 5 );
			HWRITE(lineaOut, var_readR2, RIGHT, 5 );
			HWRITE(lineaOut, var_SHAMT, RIGHT, 6 );
			HWRITE(lineaOut, var_WriteRegister, RIGHT, 5 );
			HWRITE(lineaOut, var_WriteData, RIGHT, 5 );
			WRITE(lineaOut, var_wr, RIGHT, 5 );
			WRITE(lineaOut, var_she, RIGHT, 5 );
			WRITE(lineaOut, var_dir, RIGHT, 5 );
			
			--Se escriben salidas
			HWRITE(lineaOut, var_ReadData1, RIGHT, 5 );
			HWRITE(lineaOut, var_ReadData2, RIGHT, 5 );
			
			WRITELINE(archivoSalida, lineaOut);
			
		END LOOP;
		
		--Se cierran archivos de texto
		FILE_CLOSE(archivoEntrada);
		FILE_CLOSE(archivoSalida);
		WAIT;
   end process;

END;


