<template>
	<div class="container">
<!--		<div class="text-aling-center">Calorie Tracker</div>-->
<!--		<div class="col-md-6 border">-->
			<div class="row">
				<div class="col-md-6">
					<p>Calories</p>
					<div class="circle-wrap">
						<div class="circle">
							<div class="mask half">
								<div class="fill"></div>
							</div>
							<div class="mask full">
								<div class="fill"></div>
							</div>
						</div>
						
						<div class="inside-circle"> {{ this.calories }}</div>
					</div>
				</div>
				<div class="col-md-6 m-auto">
					<div>
						<p>Calorie goal 2100</p>
                        <b v-if="this.calories > this.calorieLimit" class="text-danger">Warning: Daily calorie intake exceeded</b>
					</div>
				</div>
<!--			</div>-->
		</div>
        <div class="row">
            <div class="col-md-8 m-auto">
                <h3>What did you eat today?</h3>
                <div class="input-group my-3">
                    <input type="text" v-model="searchTerm" class="form-control" placeholder="Search for food" aria-label="Food" aria-describedby="basic-addon1">
                </div>
                <div class="input-group" v-for="food in filterByTerm.slice(0, 3)" :key="food.id">
    <!--            <div class="input-group" v-for="food in filterByTerm" :key="food.id">-->
                    <button class="w-100 btn btn-outline-secondary rounded btn-light mb-2" @click="addPrice(food.id)">{{ food.name }}</button>
    <!--                <input type="text" name="popup" id="popup" class="hide">-->

                    <div :id="'popup' + food.id" class="input-group hide">
                        <input type="number" class="form-control" aria-label="price" placeholder="Price" aria-describedby="button-addon2"
                               v-model="price" step="any">
                        <button class="btn btn-outline-secondary" type="button" id="button-addon2" @click="newEntry(food.id)">Add</button>
    <!--                    <button class="btn btn-outline-secondary" type="button" id="button-addon2" >Button</button>-->
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-8 m-auto">
                <label for="">From</label>
                <input type="date" v-model="startDate">

                <label for="">To</label>
                <input type="date" v-model="endDate">

                <table class="table">
                    <thead>
                        <tr>
                            <th class="scope">Name</th>
                            <th class="scope">Calories</th>
                            <th class="scope">Date</th>
                        </tr>
                    </thead>
                    <tbody>
                    <tr v-for="food in filterItem" :key="food.id">
                        <td>{{ food.food.name }}</td>
                        <td>{{ food.food.calorieCount }}</td>
                        <td>{{ food.createdAt }}</td>
                    </tr>
                    </tbody>
                </table>
    <!--            <div v-for="food in myFoods" :key="food.id">-->
    <!--                <p>{{ food.food.name }}</p>-->
    <!--                <p>{{ food.food.calorieCount }}</p>-->
    <!--                <p>{{ food.createdAt}}</p>-->
    <!--                <button class="btn btn-danger">Delete</button>-->
    <!--            </div>-->
                <div>
                    <p>Monthly spendings: € {{ this.totalPrice }}</p>
                    <b v-if="this.totalPrice > 1000" class="text-danger">Warning: Limit exceeded of 1000</b>
                </div>
            </div>
        </div>
	</div>
</template>

<script>
export default {
	name: 'HelloWorld',
    inject: ["foodService"],
	props: {
		msg: String
	},
    async created() {
        window.sessionStorage.setItem("Auth_key", "1")
        this.foods = await this.foodService.findAll();
        this.myFoods = await this.foodService.getUserFoods(this.auth_key)
        this.getData();
        this.getTotalSpendings()
        this.getTotalCalories()
        this.circle()
    },
    data() {
        return{
            foods: [],
            myFoods: [],
            searchTerm: "",
            price: 0,
            auth_key: window.sessionStorage.getItem("Auth_key"),
            currentDate: "",
            totalPrice: 0,
            calories: 0,
            circleDeg: 0,
            calorieLimit: 2100,
            startDate: null,
            endDate: null,
        }
    },
    methods: {
        addPrice(id) {
            const text = document.getElementById("popup" + id);
            text.classList.toggle("hide");
            text.classList.toggle("flex");
        },

        getData() {
            const date = new Date();

            let currentMonths = date.getMonth() + 1;
            currentMonths = ("0" + currentMonths).slice(-2);

            let currentDays = date.getDate();
            currentDays = ("0" + currentDays).slice(-2);

            this.currentDate = date.getFullYear()+"-"+currentMonths+"-"+currentDays;
        },

        async newEntry(id) {
            this.addPrice(id)
            this.getData()
            console.log(this.price, parseFloat(this.auth_key), id, this.currentDate)

            await this.foodService.newEntry(this.price, this.auth_key, id, this.currentDate)

            this.price = 0;
            this.myFoods = await this.foodService.getUserFoods(this.auth_key)

            this.getTotalSpendings()
            this.getTotalCalories()
            this.circle()
        },

        getTotalSpendings() {
            this.totalPrice = 0
            let currentMonth = String(new Date().getMonth() + 1)
            currentMonth = ("0" + currentMonth).slice(-2);
            let currentYear = String(new Date().getFullYear())

            for (let i = 0; i < this.myFoods.length; i++) {
                let foodDate = this.myFoods[i].createdAt.split('-')
                if (foodDate[0] === currentYear && foodDate[1] === currentMonth) {
                    this.totalPrice += this.myFoods[i].price
                }
            }
        },

        getTotalCalories() {
            this.calories = 0
            for (let i = 0; i < this.myFoods.length; i++) {
                if (this.currentDate === this.myFoods[i].createdAt) {
                    console.log(new Date(this.myFoods[i].createdAt).getMonth()+1)
                    this.calories += this.myFoods[i].food.calorieCount
                }
            }
        },

        getWeek() {

        },

        circle() {
            this.circleDeg = 180/this.calorieLimit*this.calories
            if (this.calories > this.calorieLimit) {
                this.circleDeg = 180
            }
            document.querySelector(".mask.full,.circle .fill").style.transform = this.circleDeg+"deg"
            document.documentElement.style.setProperty("--circleDeg", this.circleDeg+"deg")
        }
    },
    computed: {
        filterByTerm() {
            return this.foods.filter(food => {
                return food.name.toLowerCase().includes(this.searchTerm);
            });
        },
        filterItem() {
            let startDate = this.startDate;
            let endDate = this.endDate;

            return this.myFoods.filter(item => {
                const itemDate = item.createdAt;
                    if (startDate && endDate) {
                        return startDate <= itemDate && itemDate <= endDate;
                    }
                    if (startDate && !endDate) {
                        return startDate <= itemDate;
                    }
                    if (!startDate && endDate) {
                        return itemDate <= endDate;
                    }
                    // if (itemDate === this.currentDate) {
                    //     this.todayFood += itemDate
                    // }
                    // console.log(this.todayFood)
                    return itemDate === this.currentDate;
                })
        }
    },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    :root {
        --circleDeg: 0deg;
    }

    * {
        background-color: #282838;
        color: #fff;
    }

    body {
        margin: 0;
        font-family: Helvetica, sans-serif;
    }

	h3 {
		margin: 40px 0 0;
	}
	ul {
		list-style-type: none;
		padding: 0;
	}
	li {
		display: inline-block;
		margin: 0 10px;
	}
	a {
		color: #42b983;
	}

    input[type="text"], textarea {
        background-color: #fff;
    }

    th, td {
        background-color: #282838;
        color: #fff;
    }

    .row:first-child {
        border: solid 2px grey;
        border-radius: 15px;
        padding: 20px 0;
    }

    .row:last-child {
        margin-top: 40px;
    }

	.circle-wrap {
		margin: auto;
		width: 150px;
		height: 150px;
		background: #fefcff;
		border-radius: 50%;
		border: 1px solid #cdcbd0;
	}

	.circle-wrap .circle .mask,
	.circle-wrap .circle .fill {
		width: 150px;
		height: 150px;
		position: absolute;
		border-radius: 50%;
	}

	.mask .fill {
		clip: rect(0px, 75px, 150px, 0px);
		background-color: #227ded;
	}

	.circle-wrap .circle .mask {
		clip: rect(0px, 150px, 150px, 75px);
	}

    .mask.full {
        background-color: #fff;
    }

	.mask.full,
	.circle .fill {
		animation: fill ease-in-out 3s;
		transform: rotate(var(--circleDeg));
	}

	.circle-wrap .inside-circle {
		width: 122px;
		height: 122px;
		border-radius: 50%;
		background: #d2eaf1;
		line-height: 120px;
		text-align: center;
		margin-top: 14px;
		margin-left: 14px;
		color: #1e51dc;
		position: absolute;
		z-index: 100;
		font-weight: 700;
		font-size: 2em;
	}

    .hide {
        display: none;
    }

    .show {
        display: block;
    }

	@keyframes fill{
		0% {
			transform: rotate(0deg);
		}
		100% {
			transform: rotate(var(--circleDeg));
		}
}
</style>
