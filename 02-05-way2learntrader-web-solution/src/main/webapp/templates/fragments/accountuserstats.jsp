<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<c:if test="${account != null}">

 <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">User Statistics</h3>
        </div>
        <div class="panel-body">
            <table class="table">
                <tr>
                    <td>Full Name</td>
                    <td >${account.fullname}</td>
                </tr>
                <tr>
                    <td>User ID / Email</td>
                    <td >${account.userid}  /  ${account.email}</td>
                </tr>
                <tr>
                    <td>Creation Date</td>
                    <td >${account.creationdate}</td>
                </tr>
                <tr>
                    <td>Total Logins</td>
                    <td >${account.logincount}</td>
                </tr>
                <tr>
                    <td>Session Creation Date</td>
                    <td >${account.lastlogin}</td>
                </tr>
                <tr>
                    <td>Open Balance</td>
                    <td>${account.openbalance}</td>
                </tr>
            </table>
        </div>
    </div>

</c:if>


   