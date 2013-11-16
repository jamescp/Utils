--[[
	-=[Lua Utils]=-
--]]
--[[
	Copia uma tabela, devolvendo uma referência a uma copia exata da mesma ou nulo
--]]
function copyTable(value)
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

--[[
	Pesquisa Binária, retorna o indice de um dado elemento no vetor ordenado ou -1 caso o elemento não exista
--]]
function bsearch(vetor, elemento)
	local i, f = 1, #vetor
	local m
	while (i <= f) do
		m = math.floor((i+f)/2)
		print(i.." "..m.." "..f)
		if(elemento < vetor[m]) then
			f = m-1
		elseif (elemento > vetor[m]) then
			i = m+1
		else
			return m
		end
	end
	return -1
end

function naiveSqrt(valor, inicial)
	local k = 10E-10
	local r = inicial
	while math.abs((r*r) - valor) > k do
		print(r)
		r = (r+(valor/r))/2
	end
	return r
end

print(naiveSqrt(25,8,2))