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
            <script>
                setTimeout(() => {
                    var map = document.getElementById("map");
                    var url = "${restaurant.directions.mapUrl}";
                    url = url.replace("amp;", "");
                    url = url.replace("&amp;", "and");
                    map.setAttribute("src",url);
                }, 100);
                
            </script>
            <div class="hero-section hero-style-5 img-bg" style="background-image: url('/assets/img/hero/hero-5/hero-bg.svg'); padding: 120px 0 50px">
                <div class="container">
                    
                    <div class="row">
                    <div class="col-lg-8 modal-dialog" style="max-width: 800px">
                            <div class="modal-content rounded-6">
                             <div class="hero-content-wrapper modal-body p-4">
                                <p class=" wow fadeInUp" data-wow-delay=".4s">
                                <#if planId??>
                                	
                                	<!--These variables are obtained from the model in the ResultController (model.AddAtribute(...)) -->
                                	<#if restaurant.basicInfo.imageUrl??>
                                	<div style=“width:200px; height:200px”>
                                		<img src="${restaurant.basicInfo.imageUrl}" class="img-fluid" style="max-height:400px" />
                                	</div>
                                	</#if>
                                	<h2 class="mb-30 wow fadeInUp" data-wow-delay=".2s">${restaurant.basicInfo.locationName}</h2>
                                	
                                	<p><b>Rating:</b> ${restaurant.basicInfo.starRating}</p>
                                	<#if plan??>
                                	<p><b>Your Rating:</b> ${plan.rating}</p>
                                	</#if>
							        <p><b>Address:</b> ${restaurant.basicInfo.fullAddress}</p>
							        <p><b>Phone:</b> ${restaurant.basicInfo.phoneNumber}</p>
							        <p><b>Price:</b> ${restaurant.basicInfo.priceRating}</p>
                                                                <hr>
                                                                <br>
							        <#if weather??>
							        <p><h6><b>Currently:</b><img src="${weather.iconURL}" /> ${weather.temp} | ${weather.description}</h6> </p>
							        <#else>
							        <p><b>Currently:</b> No weather info given</p>
							        </#if>
                                                                <br>
							        <br>
							        <p><form method="post" action="/user/rate-plan" class="needs-validation">
                                	<input type="hidden" name="planId" value="${planId}" />
                                	<div class="form-group">
                  					
                                	<select name="rating" class="form-select-lg" required>  
                                            			<option selected disabled value>Rating</option>           	
                                                        <option value="1">1</option>
                                                        <option value="2">2</option>
                                                        <option value="3">3</option>
                                                        <option value="4">4</option>
                                                        <option value="5">5</option>
                                                    </select>
                                                    <button type="submit" class="button button-md radius-50 wow fadeInUp" data-wow-delay=".6s" >Rate Plan</button></div>
                                	</form>
                                	</div>
                                	</p>
                                                                <br>
                                                                <br>
                                                                <#if restaurant.directions.mapUrl??>
							        
							        
							        <#else>
							        </#if>
							    </p>
                                                            <iframe id="map"  width="100%"  height="450"  style="border:0"  src=””></iframe>
                        							    <div class="row">
							    	<div class="col-lg-6 modal-dialog">
							    		<div class="modal-content rounded-6">
							    			<div class="hero-content-wrapper modal-body p-4">
							    			<h4>The Good News</h4>
							    			<p>Rating: ${restaurant.reviews.goodReview.rating}</p>
							    			<p>${restaurant.reviews.goodReview.text}</p>
							    			</div>
							    		</div>
							    	</div>
							    	<div class="col-lg-6 modal-dialog">
							    		<div class="modal-content rounded-6">
							    			<div class="hero-content-wrapper modal-body p-4">
							    			<h4>The Bad News</h4>
							    			<p>Rating: ${restaurant.reviews.badReview.rating}</p>
							    			<p>${restaurant.reviews.badReview.text}</p>
							    			</div>
							    		</div>
							    	</div>
							    </div>
                             <#else>
                             You may have bookmarked this page by accident, click here to log in to see your plans!
                             </#if>
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