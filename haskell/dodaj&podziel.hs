--ZADANIE SKOŃCZONE
dodaj_i_podziel = map (\ x -> (x+1)/2)

dodaj_i_podzielSec = map ((/2) . (+1))

dodaj_i_podzielThr = map (/2 ) . map ((+1))

main :: IO()

--main = print(dodaj_i_podziel[5])
--main = print(dodaj_i_podzielSec[5])
main = print(dodaj_i_podzielThr[5])
