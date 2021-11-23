-- ZADANIE SKOŃCZONE/POPRAWIONE

srednia list = sum(list) / fromIntegral(length list)

sredniaSec list = foldl(\ x y -> x + y) 0 list / fromIntegral(length list)

splaszcz (list:e) = foldl(\ x y -> x++y)list e

iloczyn list = map(\ i -> (i * length list)) list

iloczyn1 list = map(*length list) list

main :: IO()
--main = print(srednia[1, 2, 3])
--main = print(sredniaSec[1, 2, 3])
--main = print(splaszcz [[1, 2, 3], [4, 5], [6, 7, 8, 9]])
--main = print(iloczyn[1, 2, 3])
main = print(iloczyn1[1, 2, 3])