import { useSelector } from "react-redux";

function Header(){
    const state=useSelector((state)=>state);
    console.log("Header ",state.loggedin.Username)
    return (
        <div className="jumbotron p-2 mb-0 rounded-0 bg-success text-white">
            {/* <img src={'shop.jpg'} style={{width:"200px", height:"60px"}} className="float-left"/> */}
            {state.loggedin.IsLoggedIn ?
            <>            
            {/* <h5 className="float-left">Role : {state.loggedin.Role}</h5> */}
            <h5 className="float-right">Hello {state.loggedin.Username}</h5> </>:
            ''}
            <h4 className="text-center">UPCYCLE : BEST OUT OF WASTE</h4>
            <div className="clearfix"></div>
        </div>
    )
}

export default Header;