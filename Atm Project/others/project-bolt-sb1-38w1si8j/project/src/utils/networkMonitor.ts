import axios from 'axios';

export class NetworkMonitor {
  private static readonly MIN_SPEED_MBPS = 1;
  private static readonly MAX_LATENCY_MS = 1000;
  private static readonly MAX_PACKET_LOSS = 5;
  private static readonly TEST_URL = 'https://api.example.com/test'; // Replace with your API endpoint

  static async checkNetworkConditions(): Promise<{ 
    isOk: boolean; 
    message: string;
  }> {
    try {
      const startTime = Date.now();
      const speed = await this.measureSpeed();
      const latency = await this.measureLatency();
      const packetLoss = await this.measurePacketLoss();

      // Simulate processing time (2-4 seconds)
      await new Promise(resolve => setTimeout(resolve, 2000 + Math.random() * 2000));

      if (speed < this.MIN_SPEED_MBPS) {
        return {
          isOk: false,
          message: `Network speed too low (${speed.toFixed(1)} Mbps). Please try again later.`
        };
      }

      if (latency > this.MAX_LATENCY_MS) {
        return {
          isOk: false,
          message: `High network latency (${latency}ms). Please try again later.`
        };
      }

      if (packetLoss > this.MAX_PACKET_LOSS) {
        return {
          isOk: false,
          message: `High packet loss (${packetLoss.toFixed(1)}%). Please try again later.`
        };
      }

      return {
        isOk: true,
        message: 'Network conditions are good. Proceeding with transaction.'
      };
    } catch (error) {
      return {
        isOk: false,
        message: 'Unable to verify network conditions. Please try again later.'
      };
    }
  }

  private static async measureSpeed(): Promise<number> {
    const startTime = Date.now();
    try {
      await axios.get(this.TEST_URL);
      const endTime = Date.now();
      const duration = (endTime - startTime) / 1000; // seconds
      const fileSize = 1; // MB (approximate size of test response)
      return fileSize / duration;
    } catch {
      return Math.random() * 10; // Simulated speed for demo
    }
  }

  private static async measureLatency(): Promise<number> {
    const startTime = Date.now();
    try {
      await axios.get(this.TEST_URL);
      return Date.now() - startTime;
    } catch {
      return Math.random() * 1000; // Simulated latency for demo
    }
  }

  private static async measurePacketLoss(): Promise<number> {
    // Simulate packet loss measurement
    return Math.random() * 10;
  }
}