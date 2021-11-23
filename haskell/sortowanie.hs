--ZADANIE SKOŃCZONE/POPRAWIONE

insert :: Ord a => a -> [a] -> [a]
insert x item = [y | y <- item, y <= x] ++ x : [y | y <- item, y > x]

sort :: Ord a => [a] -> [a]
sort = foldr insert []

main :: IO ()
main = do
  let list = [0, 1, 2, 3, 4, 5]
  let unsorted = [5, 2, 6, 8, 10, 2, 1]

  print (insert 6 list)
  print (sort unsorted)
  