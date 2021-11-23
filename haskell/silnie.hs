--ZADANIE SKOŃCZONE

silnia x | x < 0 = -1 | x == 0 = 1
 | otherwise = x * silnia(x-1)

silniaSec x = case x > 0 of
 True -> x * silniaSec(x-1)
 False -> if x < 0 then -1
                    else 1
                    
main :: IO()

main = print(silniaSec(5))
