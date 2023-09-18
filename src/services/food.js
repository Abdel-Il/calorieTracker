export class FoodAdaptor {
    RESOURCES_URL;

    constructor(resourcesUrl) {
        this.RESOURCES_URL = resourcesUrl;
    }

    async fetchJson(url, options = null) {
        let response = await fetch(url, options)
        if (response.ok) {
            return await response.json();
        } else {
            console.log(response, !response.bodyUsed ? await response.text() : "");
            return null;
        }
    }

    async findAll() {
        console.log("Food.asyncFindAll()...");
        return await this.fetchJson(this.RESOURCES_URL + "/food")
    }

    async newEntry(price, userId, foodId, createdAt) {
        return await this.fetchJson(this.RESOURCES_URL + "/userFood",
            {
                method: "POST",
                headers: {
                    "Content-type": "application/json"
                },
                body: JSON.stringify({
                    price: price,
                    user_id: userId,
                    food_id: foodId,
                    created_at: createdAt
                })
            });
    }

    async getUserFoods(id) {
        return await this.fetchJson(this.RESOURCES_URL + "/userFood/" + id,
            {
                method: "GET",
                headers: {
                    "Content-type": "application/json"
                }
            });
    }
}

