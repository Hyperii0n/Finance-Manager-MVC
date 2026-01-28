const ctxBarras = document.getElementById('columns').getContext('2d');

const gradientBar = ctxBarras.createLinearGradient(0, 0, 0, 400);
gradientBar.addColorStop(0, 'rgba(135, 135, 255, 1)');   
gradientBar.addColorStop(1, 'rgba(135, 135, 255, 0.4)'); 

new Chart(ctxBarras, {
    type: 'bar',
    data: {
        labels: ['Ago', 'Sep', 'Oct', 'Nov', 'Dic', 'Ene'],
        datasets: [{
            label: 'Gastos',
            data: [12000, 19000, 3000, 5000, 20000, 30000],
            
            backgroundColor: '#8787ff', 
            
            hoverBackgroundColor: '#ffffff', 
            
            borderRadius: 6, 
            barPercentage: 0.6,
            borderSkipped: false, 
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            legend: { display: false },
            tooltip: {
                backgroundColor: '#1b1d22',
                titleColor: '#fff',
                bodyColor: '#ccc',
                borderColor: 'rgba(255,255,255,0.1)',
                borderWidth: 1,
                padding: 10,
                displayColors: false, 
            }
        },
        scales: {
            y: {
                border: { display: false }, 
                grid: {
                    color: 'rgba(255, 255, 255, 0.05)',
                    drawTicks: false,
                },
                ticks: { 
                    color: '#64748b', 
                    font: { size: 11 }
                }
            },
            x: {
                grid: { display: false },
                ticks: { 
                    color: '#64748b',
                    font: { size: 11 }
                }
            }
        }
    }
});

const ctxDona = document.getElementById('doughnut').getContext('2d');

new Chart(ctxDona, {
    type: 'doughnut',
    data: {
        labels: ['Comida', 'Alquiler', 'Ocio'],
        datasets: [{
            data: [300, 500, 100],
            
            backgroundColor: [
                '#f472b6', 
                '#8787ff', 
                '#2dd4bf'  
            ],
            
            borderColor: '#23272e', 
            borderWidth: 5, 
            
            hoverBackgroundColor: [
                '#fbcfe8', 
                '#a5a5ff',
                '#99f6e4' 
            ],
            
            hoverOffset: 6
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        cutout: '75%',
        plugins: {
            legend: {
                position: 'right',
                labels: {
                    color: '#94a3b8',
                    usePointStyle: true,
                    pointStyle: 'circle', 
                    padding: 20,
                    font: { size: 12, family: 'system-ui' }
                }
            },
            tooltip: {
                backgroundColor: '#1b1d22',
                bodyColor: '#fff',
                borderColor: 'rgba(255,255,255,0.1)',
                borderWidth: 1,
                callbacks: {
                    label: function(context) {
                        return ' ' + context.label + ': $' + context.raw;
                    }
                }
            }
        }
    }
});