--[[
	-=[Estruturas de Dados]=-
--]]
----------------------------------------------------------------------------------------------------
FilaCircular = {
	
	metaFila = __call = function new( )
		local o ={
			valores = {},
			qtos, inicio, fim = 0,0,0,

			o:queue = function (v)
				self.valores[self.qtos] = v;
				self.qtos, self.fim = self.qtos +  1, self.fim + 1;
				self.fim = (self.fim+1)%qtos
			end,

			o:dequeue = function ()
				assert(self.qtos>0, "Erro: Fila Vazia");
				r = self.valores[inicio];
				self.qtos = self.qtos-1, self.inicio+1;
				return r
			end,

			o:first = function ()
				return self.valores[0]
			end,

			o:isEmpty = function ()
				return self.qtos==0;
			end
		}
		return o
	end
}

setmetatable(FilaCircular, FilaCircular.metaFila)
----------------------------------------------------------------------------------------------------

Pilha = {
	metaPilha = __call = function new ()
		local o = {
			qtos = 0,
			values = {},

			o:push = function(v)
				table.insert(self.values, v)
				self.qtos = self.qtos + 1
			end

			o:pop = function()
				assert(self.qtos > 0, "Erro: Pilha Vazia")
				local r = self.values[1]
				table.remove(self.values, 1)
				self.qtos = self.qtos-1
				return r
			end

			o:peek = function()
				return self.values[1]
			end

			o:isEmpty = function()
				return qtos == 0
			end	

			o:size = function()
				return qtos
			end
		}
	end
}

setmetatable(Pilha, Pilha.metaPilha)

----------------------------------------------------------------------------------------------------