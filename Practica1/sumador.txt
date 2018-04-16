LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;

ENTITY sumador IS
	PORT (
		a : IN STD_LOGIC_VECTOR (3 DOWNTO 0);
		b : IN STD_LOGIC_VECTOR (3 DOWNTO 0);
		cin : IN STD_LOGIC;
		s : OUT STD_LOGIC_VECTOR (3 DOWNTO 0);
		cout : OUT STD_LOGIC
	);
END sumador;

ARCHITECTURE Behavioral OF sumador IS
BEGIN
	PROCESS (a, b, cin)
	VARIABLE p, g : STD_LOGIC_VECTOR (3 DOWNTO 0);
	VARIABLE c : STD_LOGIC_VECTOR (4 DOWNTO 0);
	VARIABLE aux1, aux2, aux3 : std_logic;
	BEGIN
		c(0) := cin;
		for_general : FOR i IN 0 TO 3 LOOP
			p(i) := a(i) XOR b(i);
			g(i) := a(i) AND b(i);
			s(i) <= p(i) XOR c(i);
 
			aux1 := '1';
			for_aux1 : FOR j IN 0 TO i LOOP
				aux1 := aux1 AND p(j);
			END LOOP for_aux1;
			aux1 := c(0) AND aux1;
 
			aux2 := '0';
 
			for_aux2 : FOR j IN 0 TO (i-1) LOOP
				aux3 := '1';
				for_aux3 : FOR k IN (j+1) TO i LOOP
					aux3 := aux3 AND p(k);
				END LOOP for_aux3;
				aux3 := aux3 AND g(j);
				aux2 := aux2 OR aux3;
			END LOOP for_aux2;
 
			c(i+1) := g(i) OR aux1 OR aux2;
 
		END LOOP for_general;
		-- s <= sv;
		cout <= c(4);
	END PROCESS;
    
END Behavioral;