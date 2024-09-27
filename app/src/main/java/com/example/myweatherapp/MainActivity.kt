import android.os.Bundle

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observe ViewModel data
        viewModel.weatherData.observe(this) { weather ->
            binding.tvTemperature.text = "${weather.temperature}°C"
            binding.tvDescription.text = weather.description
            Glide.with(this).load(weather.iconUrl).into(binding.ivWeatherIcon)
        }

        viewModel.errorMessage.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }

        // Search for a city
        binding.btnSearch.setOnClickListener {
            val cityName = binding.etCityName.text.toString()
            if (cityName.isNotEmpty()) {
                viewModel.getWeather(cityName, YOUR_API_KEY)
            } else {
                Toast.makeText(this, "Enter a city name", Toast.LENGTH_SHORT).show()
            }
        }

        // Load last searched city on app start
        viewModel.loadLastSearchedCity()
    }
}
