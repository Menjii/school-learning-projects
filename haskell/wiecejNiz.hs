--ZADANIE SKOŃCZONE/POPRAWIONE

moreThan :: Eq a => Int -> [a] -> [a]
moreThan n item = [item | (item, n') <- foldr f [] item, n' > n]
  where
    f :: Eq a => a -> [(a, Int)] -> [(a, Int)]
    f item ((y, n') : value)
      | item == y = (y, n' + 1) : value
      | otherwise = (y, n') : f item value
    f item _ = [(item, 1)]

main :: IO ()
main = do
  print (moreThan 2 [1, 2, 3, 6, 3, 2, 3, 2])
  print (moreThan 2 [] :: [Int])
  print (moreThan 1 [1, 2, 3, 6, 3, 2, 3, 2])
  print (moreThan 2 [1, 2, 3, 6, 3, 2, 3, 2])
  print (moreThan 3 [1, 2, 3, 6, 3, 2, 3, 2])
  