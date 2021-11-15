        <!-- ========================= hero-section-wrapper-5 start ========================= -->
        <section id="home" class="hero-section-wrapper-5">

            <!-- ========================= header-6 start ========================= -->
            <header class="header header-6">
                <div class="navbar-area">
                    <div class="container">
                        <div class="row align-items-center">
                            <div class="col-lg-12">
                                <nav class="navbar navbar-expand-lg">
                                    <a class="navbar-brand" href="<@spring.url ''/>">
                                        <img src="<@spring.url '/assets/img/logo/planopedia_blau.svg'/>" alt="Logo" />
                                    </a>
                                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent6" aria-controls="navbarSupportedContent6" aria-expanded="false" aria-label="Toggle navigation">
                                        <span class="toggler-icon"></span>
                                        <span class="toggler-icon"></span>
                                        <span class="toggler-icon"></span>
                                    </button>

                                    <div class="collapse navbar-collapse sub-menu-bar" id="navbarSupportedContent6">
                                        <ul id="nav6" class="navbar-nav ms-auto">
                                            <li class="nav-item">
                                                <a class="page-scroll active" href="#home">Home</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="page-scroll" href="#gallery">Technologies</a>
                                            </li>

                                            <li class="nav-item">
                                                <a class="page-scroll" href="#plan">Plan</a>
                                            </li>

                                            <li class="nav-item">
                                                <a class="page-scroll" href="#team">Team</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="page-scroll" href="#contact">Contact</a>
                                            </li>
                                            
                                            <#if user??>
                                            <li class="nav-item">
                                           	    <a class="page-scroll" href="<@spring.url '/logout'/>">Logout</a>
                                           	</li>
                                           	<li class="nav-item">
                                           		<a class="text" href="<@spring.url '/user/saved-plans'/>"><h6>${user.userName}</h6></a>
                                           	</li>
                                           	<#else>
                                           	<li class="">
                                                <a class="page-scroll" href="<@spring.url '/oauth2/authorization/google'/>"><img src="<@spring.url '/assets/img/logo/google_signin.png'/>"></a>
                                            </li>
                                            </#if>
                                            
                                        </ul>                                            
                                            	
                                          

                                    </div>
                                    <!-- navbar collapse -->
                                </nav>
                                <!-- navbar -->
                            </div>
                        </div>
                        <!-- row -->
                    </div>
                    <!-- container -->
                </div>
                <!-- navbar area -->
            </header>
            <!-- ========================= header-6 end ========================= -->

            <!-- ========================= hero-5 start ========================= -->
            <div class="hero-section hero-style-5 img-bg" style="background-image: url('assets/img/hero/hero-5/hero-bg.svg')">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="hero-content-wrapper">
                                <h2 class="mb-30 wow fadeInUp" data-wow-delay=".2s">Planning Something, But Can't Decide What?</h2>
                                <p class="mb-30 wow fadeInUp" data-wow-delay=".4s">Planopedia takes the guesswork out of planning an evening by taking your interests, the number of people in your party, and picks an evening for you!	.</p>
                                <a href="#plan" class="button button-lg radius-50 wow fadeInUp" data-wow-delay=".6s">Get Started <i class="lni lni-chevron-right"></i> </a>
                            </div>
                        </div>
                        <div class="col-lg-6 align-self-end">
                            <div class="hero-image wow fadeInUp" data-wow-delay=".5s">
                                <img src="<@spring.url '/assets/img/hero/hero-5/hero-img.svg'/>" alt="">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- ========================= hero-5 end ========================= -->

        </section>
