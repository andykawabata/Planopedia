<!DOCTYPE html>
<html class="no-js" lang="">
<#include "/includes/head.ftlh">
<#include "/includes/preloader.ftlh">
<#include "/includes/header-nonindex.ftlh">

        <!--[if lte IE 9]>
          <p class="browserupgrade">
            You are using an <strong>outdated</strong> browser. Please
            <a href="https://browsehappy.com/">upgrade your browser</a> to improve
            your experience and security.
          </p>
        <![endif]-->

            <!-- ========================= hero-5 start ========================= -->
            <div class="hero-section hero-style-5 img-bg" style="background-image: url('/assets/img/hero/hero-5/hero-bg.svg')">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-6 align-self-end">
                            <div class="hero-image wow fadeInUp" data-wow-delay=".5s">
                                <img src="<@spring.url '/assets/img/hero/hero-5/hero-img.svg'/>" alt="">
                            </div>
                        </div>
 						<div class="col-lg-6 modal-dialog">
 							<div class="modal-content rounded-6">
                             <div class="hero-content-wrapper modal-body p-4">
                                <p class=" wow fadeInUp" data-wow-delay=".4s">
                                	<!--These variables are obtained from the model in the ResultController (model.AddAtribute(...)) -->
                                	<#if restaurant.basicInfo.imageUrl??>
                                	<div style=“width:200px; height:200px”>
                                		<img src="${restaurant.basicInfo.imageUrl}" class="img-fluid" style="max-height:400px" />
                                	</div>
                                	</#if>
                                	<h2 class="mb-30 wow fadeInUp" data-wow-delay=".2s">${restaurant.basicInfo.locationName}</h2>
                                	
                                	<p><b>Rating:</b> ${restaurant.basicInfo.starRating}</p>
							        <p><b>Address:</b> ${restaurant.basicInfo.fullAddress}</p>
							        <p><b>Phone:</b> ${restaurant.basicInfo.phoneNumber}</p>
							        <p><b>Rating:</b> ${restaurant.basicInfo.priceRating}</p>
							        <#if weather??>
							        <p><h6><b>Currently:</b><img src="${weather.iconURL}" /> ${weather.temp} | ${weather.description}</h6> </p>
							        <#else>
							        <p><b>Currently:</b> No weather info given</p>
							        </#if>
							        
							        <br>
							        <p><a href="#0" class="button button-md radius-50 wow fadeInUp" data-wow-delay=".6s">Plan It <i class="lni lni-chevron-right"></i> </a></p>
							        <#if restaurant.directions.mapUrl??>
							        
							        <iframe  width="600"  height="450"  style="border:0"  loading="lazy"  allowfullscreen src=”${restaurant.directions.mapUrl}”></iframe>
							        <#else>
							        </#if>
							    </p>
                                <div id="reviews">
    <h3>Reviews</h3>
    <div class="row bg-success">
        <div class="col-12 col-md-3 col">The Good News</div>
        <div class="col-12 col-md-3 col">Rating: ${restaurant.reviews.goodReview.rating}</div>
        <div class="col-12 col-md-3 col"></div>
    </div>
    <div class="review-text bg-light">
           ${restaurant.reviews.goodReview.text}
    </div>
    <br/>
    <div class="row bg-danger">
        <div class="col-12 col-md-3 col">The Bad News</div>
        <div class="col-12 col-md-3 col">Rating: ${restaurant.reviews.badReview.rating}</div>
        <div class="col-12 col-md-3 col"></div>
    </div>
     <div class="review-text bg-light">
            ${restaurant.reviews.badReview.text}
     </div>
 </div>
                            </div>
                           </div>
                        </div>                        
                    </div>
                </div>
            </div>
            <!-- ========================= hero-5 end ========================= -->

    </section>
        <!-- ========================= hero-section-wrapper-6 end ========================= -->
<#include "/includes/footer.ftlh">
</body>
</html>