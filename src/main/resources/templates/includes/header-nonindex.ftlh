<section id="home" class="hero-section-wrapper-5">
            <!-- ========================= header-6 start ========================= -->
            <#import "/spring.ftl" as spring />   
            <header class="header header-6">
                <div class="navbar-area">
                    <div class="container">
                        <div class="row align-items-center">
                            <div class="col-lg-12">
                                <nav class="navbar navbar-expand-lg">
                                    <a class="navbar-brand" href="<@spring.url '/'/>">
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
                                                <a class="page-scroll active" href="/#home">Home</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="page-scroll" href="/#about">About</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="page-scroll" href="/#gallery">Technologies</a>
                                            </li>

                                            <li class="nav-item">
                                                <a class="page-scroll" href="/#plan">Plan</a>
                                            </li>

                                            <li class="nav-item">
                                                <a class="page-scroll" href="/#team">Team</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="page-scroll" href="/#contact">Contact</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="page-scroll" href="<@spring.url '/oauth2/authorization/google' />">Login</a>
                                            </li>
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
