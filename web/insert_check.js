/**
 * Created by malarv on 2/9/2015.
 */
function check()
{
    var id=document.forms["myForm"]["id"].value;
    var e=document.forms["myForm"]["exp"].value
    var a=document.forms["myForm"]["actual"].value;
    var dt=document.forms["myForm"]["date"].value;

    if(id==null || id=="") {
        alert("Test id cannot be left empty");
        return false;
    }
    if(e==null || e=="") {
        alert("Expected Output cannot be left empty.")
        return false;
    }
    if(a==null || a=="") {
        alert("Actual Output cannot be left empty.")
        return false;
    }
    if(dt==null || dt=="") {
        alert("Date cannot be left empty.")
        return false;
    }
    return true;
}