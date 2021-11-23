--ZADANIE SKONCZONE/POPRAWIONE

main :: IO ()

main = do
  putStrLn "Podaj wysokość choinki: "
  height <- readLn :: IO Int
  expanseLoop height 1
  if height /= 7
    then main
    else return ()

expanseLoop 0 i = return ()
expanseLoop n i = do
  spaceLoop (n -1)
  iterateLoop i
  putStr "\n"
  expanseLoop (n -1) (i + 2)

spaceLoop 0 = return ()
spaceLoop n = do
  putStr " "
  spaceLoop (n -1)

iterateLoop 0 = return ()
iterateLoop n = do
  putStr "*"
  iterateLoop (n -1)
  