<h1>Result Page</h1>
<body>
    <p><b>Name:</b> ${restaurant.basicInfo.locationName}</p>   <!--These variables are obtained from the model in the ResultController (model.AddAtribute(...)) -->
    <p><b>Address:</b> ${restaurant.basicInfo.fullAddress}</p>
    <p><b>Phone:</b> ${restaurant.basicInfo.phoneNumber}</p>
    <p><b>Price:</b> ${restaurant.basicInfo.priceRating}</p>
    <p><b>Rating:</b> ${restaurant.basicInfo.starRating}</p>
</body>