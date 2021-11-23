-- ZADANIE SKOŃCZONE/POPRAWIONE

searchNormal :: Int -> [Int] -> Int
searchNormal searchingElem item = searchNormal searchingElem item (-1) 0
  where
    searchNormal element (value : array) index i =
      if array == []
        then index
        else
          if value == element
            then searchNormal element array i (i + 1)
            else searchNormal element array index (i + 1)

searchRecursion :: Int -> [Int] -> Int
searchRecursion searchingElem item = searchRecursion searchingElem item (-1) 0
  where
    searchRecursion element (value : array) index i
      | array == [] = index
      | element == value = i
      | otherwise = searchRecursion element array index (i + 1)

main :: IO ()
main = do
  let list = [0, 1, 2, 3, 4, 5]
  print(searchNormal 4 list)
  print(searchNormal 7 list)
  print(searchRecursion 4 list)
  print(searchRecursion 7 list)
  