 <nav id="sidebar" class="">
    <div class="sidebar-header">
        <img src="/PROJECT/assets/img/bootstraper-logo.png" alt="bootraper logo" class="app-logo">
    </div>
    <ul class="list-unstyled components text-secondary">
        <!-- dashboard -->
        <li>
            <a href="dashboard.html"><i class="fas fa-home"></i>Dashboard</a>
        </li>
      
        <!-- product -->
         <li>
            <a href="#product" data-bs-toggle="collapse" aria-expanded="false" class="dropdown-toggle no-caret-down"><i class="fas fa-user-shield"></i>Product</a>
            <ul class="collapse list-unstyled" id="product">
                <li>
                    <a href="/PROJECT/product"><i class="fas fa-lock"></i>List</a>
                </li>
                <li>
                    <a href="/PROJECT/product/create"><i class="fas fa-user-plus"></i>Add</a>
                </li>
            </ul>
        </li>

        <li>
            <a href="#authmenu" data-bs-toggle="collapse" aria-expanded="false" class="dropdown-toggle no-caret-down"><i class="fas fa-user-shield"></i>Authentication</a>
            <ul class="collapse list-unstyled" id="authmenu">
                <li>
                    <a href="login.html"><i class="fas fa-lock"></i>Login</a>
                </li>
                <li>
                    <a href="signup.html"><i class="fas fa-user-plus"></i>Signup</a>
                </li>
                <li>
                    <a href="forgot-password.html"><i class="fas fa-user-lock"></i>Forgot password</a>
                </li>
            </ul>
        </li>
    </ul>
</nav>