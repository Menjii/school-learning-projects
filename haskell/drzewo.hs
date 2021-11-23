-- ZADANIE SKOŃCZONE/POPRAWIONE
--{-# LANGUAGE OverloadedStrings #-}

data Tree a
  = EmptyNode
  | Leaf a
  | Node a (Tree a) (Tree a)

treeAltitude :: Tree b -> Int
treeAltitude EmptyNode = 0
treeAltitude (Leaf n) = 1
treeAltitude (Node _ tl tr) = 1 + max (treeAltitude tl) (treeAltitude tr)

leafAmount :: Tree b -> Int
leafAmount EmptyNode = 0
leafAmount (Leaf n) = 1
leafAmount (Node _ tl tr) = (leafAmount tl) + (leafAmount tr)

showTree :: Show a => Tree a -> Int -> String
showTree tree lvl =
  let space = replicate (3 * lvl) ' '
   in case tree of
        EmptyNode -> space ++ "()"
        Leaf item -> space ++ (show item)
        (Node item tl tr) ->
          showTree tl (lvl + 1) ++ "\n"
            ++ space
            ++ (show item)
            ++ "\n"
            ++ showTree tr (lvl + 1)

addElement :: Ord a => a -> Tree a -> Tree a
addElement a EmptyNode = Node a EmptyNode EmptyNode
addElement a (Node n tl tr)
  | a < n = Node n (addElement a tl) tr
  | a > n = Node n tl (addElement a tr)
  | otherwise = Node n tl tr
addElement a (Leaf n)
  | a < n = Node n (Leaf a) EmptyNode
  | a > n = Node n EmptyNode (Leaf a)
  | otherwise = Leaf n

treeToList :: Ord a => Tree a -> [a]
treeToList EmptyNode = []
treeToList (Leaf n) = [n]
treeToList (Node n tl tr) = treeToList tl ++ [n] ++ treeToList tr

listToTree :: Ord a => [a] -> Tree a
listToTree [] = EmptyNode
listToTree [n] = Leaf n
listToTree item =
  Node
    (item !! half)
    (listToTree $ take half item)
    (listToTree $ drop (half + 1) item)
  where
    len = length item
    half = len `div` 2

main :: IO ()
main = do
  let justTree = Node 5 (Node 3 (Leaf 2) (Leaf 3)) (Node 7 (Leaf 4) (Leaf 6))
  let altitude = show (treeAltitude justTree)
  let amount = show (leafAmount justTree)

  print ("Wysokosc drzewa: " ++ altitude ++ ", Ilosc lisci: " ++ amount)
  putStr ("\n")
  putStrLn (showTree justTree 1)

  let treeIncreased = addElement 15 justTree

  putStr ("\n")
  print ("-----------")
  putStr ("\n")

  putStr (showTree treeIncreased 1)

  putStr ("\n")
  print ("-----------")
  putStr ("\n")

  let treeToListVar = treeToList justTree
  print (treeToListVar)

  let listToTreeVar = listToTree treeToListVar
  putStr (showTree listToTreeVar 1)
  