import Vue from 'vue'

export default function init() {
	new Vue({
		data: {
			location: ''
		},
		el: '#app',
		template: '<div class="container">Now we are on {{currentLocation}}</div>',
		computed: {
			currentLocation: function() {
				return !!this.location ? this.location : 'home page';
			}
		},
		mounted: function() {
			var self = this;
			window.addEventListener("hashchange", function() {
				self.location = window.location.hash;
			});
		}
	});
}