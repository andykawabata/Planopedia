<!DOCTYPE html>
<html class="no-js" lang="">
<#include "/includes/head.ftlh">
<#include "/includes/header-nonindex.ftlh">
<body>
    <div class="container">
        <h1>Result Page</h1>
        <p><b>Name:</b> ${restaurant.basicInfo.locationName}</p>   <!--These variables are obtained from the model in the ResultController (model.AddAtribute(...)) -->
        <p><b>Address:</b> ${restaurant.basicInfo.fullAddress}</p>
        <p><b>Phone:</b> ${restaurant.basicInfo.phoneNumber}</p>
        <p><b>Price:</b> ${restaurant.basicInfo.priceRating}</p>
        <p><b>Rating:</b> ${restaurant.basicInfo.starRating}</p>
    </div>
<#include "/includes/footer.ftlh">
</body>
</html>