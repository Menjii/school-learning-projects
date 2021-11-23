import './App.css';
import Posts from "./components/posts"
import NavBar from "./components/common/navbar";
import { Route, Switch } from "react-router-dom";
import Home from "./components/home";
import NotFound from "./components/notFound"
import LoginForm from "./components/common/loginForm";

function App() {
  return (
    <div className="container-fluid">
       <NavBar />
       <div className="container">
           <div className="content">
               <Switch>
                   <Route path="/login" component={LoginForm} />
                   <Route path="/posts"
                   render={props => <Posts sortBy="newest" {...props} />}
                   />
                   <Route exact path="/" exact component={Home} />
                   <Route component={NotFound} />
               </Switch>
           </div>
       </div>
     </div>
  );
}

export default App;
