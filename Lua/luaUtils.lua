--[[
	-=[Lua Utils]=-
--]]
--[[
	Copia uma variavel, devolvendo uma referência a uma copia exata da mesma ou nulo
--]]
function deepCopy(value)
	local r = nil
	if(type(value) == "table") then
		r = {}
		for k,v in pairs(value) do
			table.insert(r, k, copy(v))
		end
	end
	return r
end
--[[
	Ordena um vetor no intervalo de 'init' a 'fim'
	retorna uma referência à uma copia ordenada do vetor
--]]
function quickSort(table, init, fim) 
	assert(type(table) == "table", "Especifique uma tabela")
	assert(type(init) == "number", "Especifique um indice inicial válido(Inteiro)")
	assert(type(fim) == "number", "Especifique um indice final válido(Inteiro)")
	assert(init > 0 and init < fim and fim < #table, "Intervalos de ordenação fora dos limites")
	local v = copyTable(table)
	local i, j, pivot
	i, j = ini, fim
	pivot = v[math.floor((ini+fim)/2)]
	while i<=j  do
		while (v[i] < pivot) do --Busca um elemento maior que o pivot
			i=i+1
		end
		while (v[j] > pivot) do --Busca um elemento menor que o pivot
			j=j-1
		end
		if (i<=j) then --Se I < J, troca a posição
			v[i], v[j]=v[j], v[i]
			i, j = i+1, j-1
		end
	end
	if j > ini then quickSort(v, ini, j) end
	if i < fim then quickSort(v, i, fim) end
	return v
end